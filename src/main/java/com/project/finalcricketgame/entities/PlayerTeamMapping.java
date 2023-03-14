package com.project.finalcricketgame.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PlayerTeamMapping")
@NoArgsConstructor
@Data
public class PlayerTeamMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int playerTeamMapping_id;
    private int player_id;
    private String team_id;


}
