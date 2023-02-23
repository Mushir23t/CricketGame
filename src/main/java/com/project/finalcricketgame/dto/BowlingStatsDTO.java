package com.project.finalcricketgame.dto;

import com.project.finalcricketgame.entities.BattingStats;
import com.project.finalcricketgame.entities.BowlingStats;

public class BowlingStatsDTO {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOvers() {
        return overs;
    }

    public void setOvers(int overs) {
        this.overs = overs;
    }

    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    private String name;
    private int overs;
    private int balls;
    private int wickets;
    private int runs;

    public BowlingStatsDTO(BowlingStats bowlingStats){
        this.name = bowlingStats.getPlayer().getName();
        this.balls = bowlingStats.getBalls();
        this.wickets = bowlingStats.getWickets();
        this.runs = bowlingStats.getRuns();
        this.overs = bowlingStats.getOvers();
    }
}
