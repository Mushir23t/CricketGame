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

import java.util.ArrayList;
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
    private static final Logger logger = LoggerFactory.getLogger(ScoreCardService.class);

    public ScoreCardDTO getScoreCardFromMongo(int match_id) {
        ScoreCardMongo scoreCardMongo = scoreCardMongoRepository.findBymatchId(match_id);
        System.out.println(scoreCardMongo);
        System.out.println("REACHED HERE");
        ScoreCardDTO scoreCardDTO = new ScoreCardDTO(scoreCardMongo);
        System.out.println(scoreCardDTO);
        return scoreCardDTO;
    }

    public ScoreCardDTO getScoreCardFromES(int match_id) {
        Optional<ScoreCard> scoreCard = scoreCardESRepository.findByMatchId(match_id);
        return scoreCard.map(ScoreCardDTO::new).orElse(null);
    }

    public void createScoreCard(int match_id) {

        MatchTeamMapping matchTeamMapping = matchTeamMappingService.findByMatchId(match_id);
        Team team1 = teamService.findTeamById(matchTeamMapping.getTeam1_id());
        Team team2 = teamService.findTeamById(matchTeamMapping.getTeam2_id());
        int firstInningsTotal = battingService.totalRuns(match_id, team1.getName());
        int firstInningsWicket = bowlingService.totalWickets(match_id, team2.getName());
        int secondInningsTotal = battingService.totalRuns(match_id, team2.getName());
        int secondInningsWicket = bowlingService.totalWickets(match_id, team1.getName());
        ArrayList<BattingStatsDTO> team1battingStats = new ArrayList<>();
        ArrayList<BowlingStatsDTO> team2bowlingStats = new ArrayList<>();
        ArrayList<BattingStatsDTO> team2battingStats = new ArrayList<>();
        ArrayList<BowlingStatsDTO> team1bowlingStats = new ArrayList<>();
        team1battingStats = BattingStats.toDTO(battingService.getStats(match_id, team1.getName()));
        team2battingStats = BattingStats.toDTO(battingService.getStats(match_id, team2.getName()));
        team1bowlingStats = BowlingStats.toDTO(bowlingService.getStats(match_id, team1.getName()));
        team2bowlingStats = BowlingStats.toDTO(bowlingService.getStats(match_id, team2.getName()));
        String id = UUID.randomUUID().toString();
        ScoreCard scoreCard = new ScoreCard(id, firstInningsTotal,
                firstInningsWicket, secondInningsTotal, secondInningsWicket, match_id,
                team1battingStats, team2bowlingStats, team2battingStats, team1bowlingStats
        );
        System.out.println(id);
        System.out.println(firstInningsTotal);
        System.out.println(secondInningsTotal);
        System.out.println(firstInningsWicket);
        System.out.println(secondInningsWicket);
        System.out.println(team1battingStats.get(0).getRunsScored());
        System.out.println(team1bowlingStats.get(0).getBalls());
        System.out.println(team2battingStats.get(0).getRunsScored());
        System.out.println(team2bowlingStats.get(0).getBalls());
        System.out.println(match_id);
        try {
            ScoreCardMongo scoreCardMongo = new ScoreCardMongo(scoreCard);
            System.out.println(scoreCardMongoRepository.findAll());
            ScoreCardMongo scoreCardMongo1 = scoreCardMongoRepository.save(scoreCardMongo);
            System.out.println(scoreCardMongoRepository.findAll());
            System.out.println("HERe" + scoreCardMongo1.getFirstInningsTotal());
            scoreCardESRepository.save(scoreCard);
        } catch (NullPointerException e) {
            logger.error("NullPointerException occurred while saving document: " + e.getMessage());
        }
    }
}
