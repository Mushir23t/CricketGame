package com.project.finalcricketgame.controller;

import com.project.finalcricketgame.dto.PlayerDTO;
import com.project.finalcricketgame.dto.TeamDTO;
import com.project.finalcricketgame.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("CricketGame")
public class TeamController {
    @Autowired
    TeamService teamService;
    private static final Logger logger = LoggerFactory.getLogger(TeamController.class);

    @PostMapping("/Team")
    public TeamDTO addTeam(@RequestBody TeamDTO teamDTO) {
        return teamService.addTeam(teamDTO);
    }

    @GetMapping("/Team/{teamId}")
    List<PlayerDTO> getTeam(@PathVariable String teamId) {
        return teamService.getTeam(teamId);
    }

    @PutMapping("/Team/{teamName}/{playerId}")
    public String addPlayer(@PathVariable String teamName, @PathVariable int playerId) {
        return teamService.addPlayerToTeam(teamName, playerId);
    }

    @DeleteMapping("/Team/{teamName}/{playerId}")
    public String removePlayer(@PathVariable String teamName, @PathVariable int playerId) {
        return teamService.removePlayerFromTeam(teamName, playerId);
    }

}
