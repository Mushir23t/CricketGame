package com.project.finalcricketgame.dto;

import com.project.finalcricketgame.entities.BattingStats;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BattingStatsDTO {

    // this is a fake comment
    private String name;
    private int ballsPlayed;
    private int runsScored;

    public BattingStatsDTO(BattingStats battingStats){
        this.name = battingStats.getPlayer().getName();
        this.ballsPlayed = battingStats.getBallsPlayed();
        this.runsScored = battingStats.getRunsScored();
    }
}


