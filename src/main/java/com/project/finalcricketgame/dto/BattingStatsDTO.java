package com.project.finalcricketgame.dto;

import com.project.finalcricketgame.entities.BattingStats;

public class BattingStatsDTO {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBallsPlayed() {
        return ballsPlayed;
    }

    public void setBallsPlayed(int ballsPlayed) {
        this.ballsPlayed = ballsPlayed;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }

    private String name;
    private int ballsPlayed;
    private int runsScored;

    public BattingStatsDTO(BattingStats battingStats){
        this.name = battingStats.getPlayer().getName();
        this.ballsPlayed = battingStats.getBallsPlayed();
        this.runsScored = battingStats.getRunsScored();
    }
}


