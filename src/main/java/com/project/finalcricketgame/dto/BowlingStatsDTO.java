package com.project.finalcricketgame.dto;

import com.project.finalcricketgame.entities.BattingStats;
import com.project.finalcricketgame.entities.BowlingStats;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BowlingStatsDTO {

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
