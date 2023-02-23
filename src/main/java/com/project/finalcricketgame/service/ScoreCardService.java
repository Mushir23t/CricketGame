package com.project.finalcricketgame.service;

import com.project.finalcricketgame.dto.BattingStatsDTO;
import com.project.finalcricketgame.dto.BowlingStatsDTO;
import com.project.finalcricketgame.dto.ScoreCardDTO;
import com.project.finalcricketgame.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

    public ScoreCardDTO getScoreCard(int match_id) {
//        boolean isValidMatch = matchService.getMatch(match_id);
//        if (isValidMatch) {
//            Match match = matchService.findMatch(match_id);
//            if (Objects.equals(match.getStatus(), "Not started yet")) {
//                return ResponseEntity.ok("Match Hasn't started yet");
//            }

            return generateScoreCard(match_id);
//        } else {
//            return ResponseEntity.ok("No such match with match_id : " + match_id);
//        }
    }

    private ScoreCardDTO generateScoreCard(int match_id) {
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
        scoreCardDTO = new ScoreCardDTO(firstInningsTotal,
                firstInningsWicket,secondInningsTotal,secondInningsWicket,
                team1battingStats,team2bowlingStats,team2battingStats,team1bowlingStats
        );
        return scoreCardDTO;
    }
}
