package com.project.finalcricketgame.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.finalcricketgame.entities.BattingStats;
import com.project.finalcricketgame.entities.BowlingStats;
import lombok.AllArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@JsonPropertyOrder({"firstInningsTotal", "FirstInningsWicket", "secondInningsTotal", "secondInningsWicket", "team1battingStats", "team2bowlingStats", "team2battingStats", "team1bowlingStats"})
public class ScoreCardDTO {

    private int firstInningsTotal;
    private int FirstInningsWicket;
    private int secondInningsTotal;
    private int secondInningsWicket;

    private ArrayList<BattingStatsDTO> team1battingStats = new ArrayList<>();
    private ArrayList<BowlingStatsDTO> team2bowlingStats = new ArrayList<>();
    private ArrayList<BattingStatsDTO> team2battingStats = new ArrayList<>();
    private ArrayList<BowlingStatsDTO> team1bowlingStats = new ArrayList<>();


    public int getFirstInningsTotal() {
        return firstInningsTotal;
    }

    public void setFirstInningsTotal(int firstInningsTotal) {
        this.firstInningsTotal = firstInningsTotal;
    }

    public int getFirstInningsWicket() {
        return FirstInningsWicket;
    }

    public void setFirstInningsWicket(int firstInningsWicket) {
        FirstInningsWicket = firstInningsWicket;
    }

    public int getSecondInningsTotal() {
        return secondInningsTotal;
    }

    public void setSecondInningsTotal(int secondInningsTotal) {
        this.secondInningsTotal = secondInningsTotal;
    }

    public int getSecondInningsWicket() {
        return secondInningsWicket;
    }

    public void setSecondInningsWicket(int secondInningsWicket) {
        this.secondInningsWicket = secondInningsWicket;
    }

    public ArrayList<BattingStatsDTO> getTeam1battingStats() {
        return team1battingStats;
    }

    public void setTeam1battingStats(ArrayList<BattingStatsDTO> team1battingStats) {
        this.team1battingStats = team1battingStats;
    }

    public ArrayList<BowlingStatsDTO> getTeam2bowlingStats() {
        return team2bowlingStats;
    }

    public void setTeam2bowlingStats(ArrayList<BowlingStatsDTO> team2bowlingStats) {
        this.team2bowlingStats = team2bowlingStats;
    }

    public ArrayList<BattingStatsDTO> getTeam2battingStats() {
        return team2battingStats;
    }

    public void setTeam2battingStats(ArrayList<BattingStatsDTO> team2battingStats) {
        this.team2battingStats = team2battingStats;
    }

    public ArrayList<BowlingStatsDTO> getTeam1bowlingStats() {
        return team1bowlingStats;
    }

    public void setTeam1bowlingStats(ArrayList<BowlingStatsDTO> team1bowlingStats) {
        this.team1bowlingStats = team1bowlingStats;
    }



}
