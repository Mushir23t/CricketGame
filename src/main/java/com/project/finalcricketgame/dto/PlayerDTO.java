package com.project.finalcricketgame.dto;

import com.project.finalcricketgame.entities.Player;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PlayerDTO {
    private int playerId;
    private String name;

    public PlayerDTO(Player player) {
        this.playerId = player.getPlayer_id();
        this.name = player.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

}

