package com.project.finalcricketgame.entities;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "innings")
@NoArgsConstructor
public class Innings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int innings_id;
    private int inn_number;
    private String batting_team_id;
    private String bowling_team_id;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "match_id")
    private Match match;

    public Innings(int inn_number, String batting_team_id, String bowling_team_id, Match match) {
        this.inn_number = inn_number;
        this.batting_team_id = batting_team_id;
        this.bowling_team_id = bowling_team_id;
        this.match = match;
    }

}
