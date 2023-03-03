package com.project.finalcricketgame.controller;

import com.project.finalcricketgame.dto.PlayerDTO;
import com.project.finalcricketgame.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("CricketGame")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @PostMapping("/addPlayer")
    String addPlayer(@RequestBody PlayerDTO playerDTO) {
        return playerService.addPlayer(playerDTO);
    }

    private static final Logger logger = LoggerFactory.getLogger(MatchController.class);

    @GetMapping("/getPlayers")
    ResponseEntity<?> getPlayers() {
        List<PlayerDTO> playerDTOS = playerService.getPlayers();
        if (playerDTOS.size() == 0) {
            logger.warn("No players are added in the database");
            return ResponseEntity.ok("No players are available");
        }
        logger.info("Players list was fetched");
        return ResponseEntity.ok(playerDTOS);
    }

    @PostMapping("/updatePlayer/{player_id}")
    String updatePlayer(@PathVariable int player_id, @RequestBody String name) {
        return playerService.updatePlayer(player_id, name);
    }

    @DeleteMapping("/deletePlayer/{player_id}")
    String deletePlayer(@PathVariable int player_id) {
        return playerService.deletePlayer(player_id);
    }


}
