package com.project.finalcricketgame.entities;

import com.project.finalcricketgame.dto.BattingStatsDTO;
import com.project.finalcricketgame.dto.BowlingStatsDTO;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "bowlingStats")
public class BowlingStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stats_id;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "match_id")
    private Match match;
    private int overs;
    private int balls;
    private int wickets;
    private int runs;
    private String team_id;


    public BowlingStats() {
    }

    public BowlingStats(Player player, Match match, String team_id) {
        this.player = player;
        this.match = match;
        this.overs = 0;
        this.balls = 0;
        this.wickets = 0;
        this.runs = 0;
        this.team_id = team_id;
    }

    public static ArrayList<BowlingStatsDTO> toDTO(ArrayList<BowlingStats> bowlingStats){
        ArrayList<BowlingStatsDTO> bdto = new ArrayList<>();
        for(BowlingStats b : bowlingStats){
            bdto.add(new BowlingStatsDTO(b));
        }
        return bdto;
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


}
