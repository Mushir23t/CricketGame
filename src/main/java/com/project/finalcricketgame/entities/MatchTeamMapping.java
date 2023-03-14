package com.project.finalcricketgame.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "matchTeamMapping")
@NoArgsConstructor
@Data
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


}
