package com.project.finalcricketgame.entities;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PlayerTeamMapping")
@NoArgsConstructor
public class PlayerTeamMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int playerTeamMapping_id;
    private int player_id;
    private String team_id;

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

}
