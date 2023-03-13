package com.project.finalcricketgame.dto;

import com.project.finalcricketgame.entities.ScoreCardES;
import com.project.finalcricketgame.entities.ScoreCardMongo;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ScoreCardDTO {
    private int firstInningsTotal;
    private int FirstInningsWicket;
    private int secondInningsTotal;
    private int secondInningsWicket;
    private int matchId;
    private ArrayList<BattingStatsDTO> team1battingStats = new ArrayList<>();
    private ArrayList<BowlingStatsDTO> team2bowlingStats = new ArrayList<>();
    private ArrayList<BattingStatsDTO> team2battingStats = new ArrayList<>();
    private ArrayList<BowlingStatsDTO> team1bowlingStats = new ArrayList<>();

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
    public ScoreCardDTO(ScoreCardMongo scoreCardMongo){
        this.firstInningsTotal = scoreCardMongo.getFirstInningsTotal();
        this.FirstInningsWicket = scoreCardMongo.getFirstInningsWicket();
        this.secondInningsTotal = scoreCardMongo.getSecondInningsTotal();
        this.secondInningsWicket = scoreCardMongo.getSecondInningsWicket();
        this.matchId = scoreCardMongo.getMatchId();
        this.team1battingStats = scoreCardMongo.getTeam1battingStats();
        this.team2battingStats = scoreCardMongo.getTeam2battingStats();
        this.team1bowlingStats = scoreCardMongo.getTeam1bowlingStats();
        this.team2bowlingStats = scoreCardMongo.getTeam2bowlingStats();
    }
}
