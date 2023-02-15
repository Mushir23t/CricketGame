package org.cricketgame.controller;

import org.cricketgame.service.PlayerStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@RestController
@RequestMapping("CricketGame")
public class PlayerStatsController {
    @Autowired
    private PlayerStatsService playerStatsService;

    @GetMapping("/{playerName}")
    @ResponseBody
    public ResponseEntity<?> getPlayerStats(@PathVariable String playerName) {
        return ResponseEntity.ok(playerStatsService.getPlayerStats(playerName));
    }

}