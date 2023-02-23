package com.project.finalcricketgame.controller;

import com.project.finalcricketgame.dto.PlayerDTO;
import com.project.finalcricketgame.dto.TeamDTO;
import com.project.finalcricketgame.entities.Player;
import com.project.finalcricketgame.repository.TeamRepository;
import com.project.finalcricketgame.service.PlayerTeamMapService;
import com.project.finalcricketgame.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("CricketGame")
public class TeamController {

    @Autowired
    TeamService teamService;

    @Autowired
    PlayerTeamMapService playerTeamMapService;
    @PostMapping("/addTeam")
    public String addTeam(@RequestBody TeamDTO teamDTO) {
        return teamService.addTeam(teamDTO);
    }

    @GetMapping("/Team/{team_id}")
    ResponseEntity<?> getTeam(@PathVariable String team_id){
        if(!Objects.equals(teamService.findById(team_id), "OK")){
            return ResponseEntity.ok("No such team exist");
        }
        List<Player> players = playerTeamMapService.findByTeam(team_id);
        List<PlayerDTO> playerDTOS = new ArrayList<>();
        for (Player player : players) {
            playerDTOS.add(new PlayerDTO(player));
        }
        return ResponseEntity.ok(playerDTOS);
    }

    @PostMapping("/addPlayer/{teamName}/{player_id}")
    public String addPlayer (@PathVariable String teamName,@PathVariable int player_id){
        return teamService.addPlayer(teamName, player_id);
    }

    @DeleteMapping("/removePlayer/{teamName}/{player_id}")
    public String removePlayer (@PathVariable String teamName,@PathVariable int player_id){
        return teamService.removePlayer(teamName, player_id);
    }

}
