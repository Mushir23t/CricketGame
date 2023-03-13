package com.project.finalcricketgame.dto;

import com.project.finalcricketgame.entities.Player;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PlayerDTO {
    private int playerId;
    private String name;

    public PlayerDTO(Player player) {
        this.playerId = player.getPlayer_id();
        this.name = player.getName();
    }

}

