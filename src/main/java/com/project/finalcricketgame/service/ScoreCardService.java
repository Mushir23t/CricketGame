package com.project.finalcricketgame.service;

import com.project.finalcricketgame.dto.BattingStatsDTO;
import com.project.finalcricketgame.dto.BowlingStatsDTO;
import com.project.finalcricketgame.dto.ScoreCardDTO;
import com.project.finalcricketgame.entities.*;
import com.project.finalcricketgame.repository.es.ScoreCardESRepository;
import com.project.finalcricketgame.repository.mongo.ScoreCardMongoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScoreCardService {

    @Autowired
    TeamService teamService;
    @Autowired
    MatchTeamMappingService matchTeamMappingService;
    @Autowired
    BattingService battingService;
    @Autowired
    BowlingService bowlingService;
    @Autowired
    ScoreCardMongoRepository scoreCardMongoRepository;
    @Autowired
    ScoreCardESRepository scoreCardESRepository;
    @Autowired
    MatchService matchService;
    private static final Logger logger = LoggerFactory.getLogger(ScoreCardService.class);

    public ScoreCardDTO getScoreCardFromMongo(int matchId) {
        if (!matchService.isValidMatch(matchId)) {
            logger.warn("Scorecard Request received , Match not started yet or no match with matchId {}", matchId);
            throw new RuntimeException("Match not played or no match with matchId:" + matchId);
        }
        logger.info("Scorecard Request received , Scorecard of Match {} fetched", matchId);
        ScoreCard scoreCard = scoreCardMongoRepository.findBymatchId(matchId);
        return new ScoreCardDTO(scoreCard);
    }

    public ScoreCardDTO getScoreCardFromES(int matchId) {
        if (!matchService.isValidMatch(matchId)) {
            logger.warn("Scorecard Request received , Match not started yet or no match with matchId {}", matchId);
            throw new RuntimeException("Match not played or no match with matchId:" + matchId);
        }
        logger.info("Scorecard Request received , Scorecard of Match {} fetched", matchId);
        Optional<ScoreCardES> scoreCard = scoreCardESRepository.findByMatchId(matchId);
        return scoreCard.map(ScoreCardDTO::new).orElse(null);
    }

    public void createScoreCard(int matchId) {

        MatchTeamMapping matchTeamMapping = matchTeamMappingService.findByMatchId(matchId);
        Team team1 = teamService.findTeamById(matchTeamMapping.getTeam1_id());
        Team team2 = teamService.findTeamById(matchTeamMapping.getTeam2_id());
        int firstInningsTotal = battingService.totalRuns(matchId, team1.getName());
        int firstInningsWicket = bowlingService.totalWickets(matchId, team2.getName());
        int secondInningsTotal = battingService.totalRuns(matchId, team2.getName());
        int secondInningsWicket = bowlingService.totalWickets(matchId, team1.getName());
        List<BattingStatsDTO> team1battingStats = BattingStats.toDTO(battingService.getStats(matchId, team1.getName()));
        List<BattingStatsDTO> team2battingStats = BattingStats.toDTO(battingService.getStats(matchId, team2.getName()));
        List<BowlingStatsDTO> team1bowlingStats = BowlingStats.toDTO(bowlingService.getStats(matchId, team1.getName()));
        List<BowlingStatsDTO> team2bowlingStats = BowlingStats.toDTO(bowlingService.getStats(matchId, team2.getName()));
        String id = UUID.randomUUID().toString();
        ScoreCardES scoreCardES = new ScoreCardES(id, firstInningsTotal,
                firstInningsWicket, secondInningsTotal, secondInningsWicket, matchId,
                team1battingStats, team2bowlingStats, team2battingStats, team1bowlingStats
        );
        try {
            ScoreCard scoreCard = new ScoreCard(scoreCardES);
            scoreCardMongoRepository.save(scoreCard);
            scoreCardESRepository.save(scoreCardES);
        } catch (NullPointerException e) {
            logger.error("NullPointerException occurred while saving document: " + e.getMessage());
            throw new NullPointerException("ES Write Response Messed up");
        }
    }
}
