package com.project.finalcricketgame.entities;

import com.project.finalcricketgame.dto.CreatePlayerDTO;
import com.project.finalcricketgame.dto.PlayerDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "player")
@NoArgsConstructor
@Data
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int player_id;
    private String name;
    private boolean isActive;

    public Player(PlayerDTO playerDTO) {
        this.name = playerDTO.getName();
        this.isActive = true;
    }

    public Player(CreatePlayerDTO createPlayerDTO){
        this.name = createPlayerDTO.getName();
    }


}
