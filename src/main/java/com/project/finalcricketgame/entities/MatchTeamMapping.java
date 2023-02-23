package com.project.finalcricketgame.entities;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "matchTeamMapping")
@NoArgsConstructor
public class MatchTeamMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matchTeamMapping_id;

    private int match_id;
    private String team1_id;
    private String team2_id;
    public MatchTeamMapping(int match_id, String team1_id, String team2_id) {
        this.match_id = match_id;
        this.team1_id = team1_id;
        this.team2_id = team2_id;
    }

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }

    public String getTeam1_id() {
        return team1_id;
    }

    public void setTeam1_id(String team1_id) {
        this.team1_id = team1_id;
    }

    public String getTeam2_id() {
        return team2_id;
    }

    public void setTeam2_id(String team2_id) {
        this.team2_id = team2_id;
    }

}
