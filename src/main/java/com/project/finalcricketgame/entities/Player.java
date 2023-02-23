package com.project.finalcricketgame.entities;

import com.project.finalcricketgame.dto.PlayerDTO;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "player")
@NoArgsConstructor
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    public int getPlayer_id() {
        return player_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
