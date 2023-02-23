package com.project.finalcricketgame.dto;

import com.project.finalcricketgame.entities.Player;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PlayerDTO {
    private int player_id;
    private String name;

    public PlayerDTO(Player player) {
        this.player_id = player.getPlayer_id();
        this.name = player.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

}

