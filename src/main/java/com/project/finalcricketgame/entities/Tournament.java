package com.project.finalcricketgame.entities;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "tournament")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tournament_id;
    private String winner;
    @Transient
    private ArrayList<Team> teams = new ArrayList<>();

    public int getTournament_id() {
        return tournament_id;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }
}
