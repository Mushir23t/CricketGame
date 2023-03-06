package com.project.finalcricketgame.service;

import com.project.finalcricketgame.dto.BattingStatsDTO;
import com.project.finalcricketgame.dto.BowlingStatsDTO;
import com.project.finalcricketgame.dto.ScoreCardDTO;
import com.project.finalcricketgame.entities.BattingStats;
import com.project.finalcricketgame.entities.BowlingStats;
import com.project.finalcricketgame.entities.MatchTeamMapping;
import com.project.finalcricketgame.entities.Team;
import com.project.finalcricketgame.repository.ScoreCardES;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class ScoreCardService {

    @Autowired
    MatchService matchService;
    @Autowired
    TeamService teamService;
    @Autowired
    MatchTeamMappingService matchTeamMappingService;

    @Autowired
    BattingService battingService;


    @Autowired
    PlayerTeamMapService playerTeamMapService;

    @Autowired
    BowlingService bowlingService;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    ScoreCardES scoreCardES;

    private static final Logger logger = LoggerFactory.getLogger(ScoreCardService.class);


    public ScoreCardDTO getScoreCard(int match_id) {
        Optional<ScoreCardDTO> scoreCardDTO = scoreCardES.findByMatchId(match_id);
        return scoreCardDTO.orElse(null);
    }


    public void  createScoreCard(int match_id) {
        ScoreCardDTO scoreCardDTO;
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
        scoreCardDTO = new ScoreCardDTO(id, firstInningsTotal,
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
        try{
            scoreCardES.save(scoreCardDTO);
        }
        catch (NullPointerException e){
            logger.error("NullPointerException occurred while saving document: " + e.getMessage());
        }
    }
}
