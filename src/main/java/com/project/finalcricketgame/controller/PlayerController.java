package com.project.finalcricketgame.controller;

import com.project.finalcricketgame.dto.CreatePlayerDTO;
import com.project.finalcricketgame.dto.PlayerDTO;
import com.project.finalcricketgame.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.NoContentException;
import javax.xml.bind.ValidationException;
import java.util.List;


@RestController
@RequestMapping("CricketGame")
public class PlayerController {

    @Autowired
    PlayerService playerService;
    private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @PostMapping("/Player")
    PlayerDTO addPlayer(@RequestBody CreatePlayerDTO player) throws ValidationException {
        return playerService.addPlayer(player);
    }

    @GetMapping("/Players")
    List<PlayerDTO> getPlayers() throws NoContentException {
        return playerService.getPlayers();
    }

    // change player name
    @PutMapping("/Player/{playerId}")
    PlayerDTO updatePlayer(@PathVariable int playerId, @RequestBody CreatePlayerDTO player) throws ValidationException {
        return playerService.updatePlayer(playerId, player);
    }

    // soft deleting player and if it is in a team, remove it.
    @DeleteMapping("Player/{playerId}")
    String deletePlayer(@PathVariable int playerId) {
        return playerService.deletePlayer(playerId);
    }


}
