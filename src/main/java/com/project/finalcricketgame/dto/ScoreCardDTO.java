package com.project.finalcricketgame.dto;

import com.project.finalcricketgame.entities.ScoreCardES;
import com.project.finalcricketgame.entities.ScoreCard;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ScoreCardDTO {
    private int firstInningsTotal;
    private int FirstInningsWicket;
    private int secondInningsTotal;
    private int secondInningsWicket;
    private int matchId;
    private List<BattingStatsDTO> team1battingStats = new ArrayList<>();
    private List<BowlingStatsDTO> team2bowlingStats = new ArrayList<>();
    private List<BattingStatsDTO> team2battingStats = new ArrayList<>();
    private List<BowlingStatsDTO> team1bowlingStats = new ArrayList<>();

    public ScoreCardDTO(ScoreCardES scoreCardES){
        this.firstInningsTotal = scoreCardES.getFirstInningsTotal();
        this.FirstInningsWicket = scoreCardES.getFirstInningsWicket();
        this.secondInningsTotal = scoreCardES.getSecondInningsTotal();
        this.secondInningsWicket = scoreCardES.getSecondInningsWicket();
        this.matchId = scoreCardES.getMatchId();
        this.team1battingStats = scoreCardES.getTeam1battingStats();
        this.team2battingStats = scoreCardES.getTeam2battingStats();
        this.team1bowlingStats = scoreCardES.getTeam1bowlingStats();
        this.team2bowlingStats = scoreCardES.getTeam2bowlingStats();
    }
    public ScoreCardDTO(ScoreCard scoreCard){
        this.firstInningsTotal = scoreCard.getFirstInningsTotal();
        this.FirstInningsWicket = scoreCard.getFirstInningsWicket();
        this.secondInningsTotal = scoreCard.getSecondInningsTotal();
        this.secondInningsWicket = scoreCard.getSecondInningsWicket();
        this.matchId = scoreCard.getMatchId();
        this.team1battingStats = scoreCard.getTeam1battingStats();
        this.team2battingStats = scoreCard.getTeam2battingStats();
        this.team1bowlingStats = scoreCard.getTeam1bowlingStats();
        this.team2bowlingStats = scoreCard.getTeam2bowlingStats();
    }
}
