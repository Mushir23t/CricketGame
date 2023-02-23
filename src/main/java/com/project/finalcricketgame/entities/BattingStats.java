package com.project.finalcricketgame.entities;

import com.project.finalcricketgame.dto.BattingStatsDTO;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "battingStats")
@NoArgsConstructor
public class BattingStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stats_id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "match_id")
    private Match match;
    private String team_id;
    private int ballsPlayed;
    private int runsScored;
    public BattingStats(Player player, Match match, String team_id) {
        this.player = player;
        this.match = match;
        this.ballsPlayed = 0;
        this.team_id = team_id;
        this.runsScored = 0;
    }

    public static ArrayList<BattingStatsDTO> toDTO(ArrayList<BattingStats> battingStats){
        ArrayList<BattingStatsDTO> bdto = new ArrayList<>();
        for(BattingStats b : battingStats){
            bdto.add(new BattingStatsDTO(b));
        }
        return bdto;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
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



}
