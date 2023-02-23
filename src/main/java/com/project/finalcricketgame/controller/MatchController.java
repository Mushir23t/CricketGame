package com.project.finalcricketgame.controller;

import com.project.finalcricketgame.service.MatchService;
import com.project.finalcricketgame.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("CricketGame")
public class MatchController {

    @Autowired
    MatchService matchService;

    @Autowired
    TeamService teamService;

    @PostMapping("/CreateMatch/{team1}/{team2}")
    String addMatch(@PathVariable String team1, @PathVariable String team2) {
        boolean teamExist = teamService.validTeam(team1);
        if(!teamExist){
            return "Team " + team1 + " doesn't exist";
        }
        teamExist = teamService.validTeam(team2);
        if(!teamExist){
            return "Team " + team2 + " doesn't exist";
        }
        int match_id = matchService.createMatch(team1, team2);
        return "Match id is " + match_id;
    }

    @PostMapping("/StartMatch/{match_id}")
    String startMatch(@PathVariable int match_id) {
        boolean teamCheck = teamService.TeamsValidity(match_id);
        if(!teamCheck){
            return "Both team must have 11 players";
        }
        if(!matchService.check(match_id)){
            return "Match already played or no match with match_id:" + match_id;
        }
        matchService.playMatch(match_id);
        return "Match Completed";
    }

    @DeleteMapping("/DeleteMatch/{match_id}")
    String deleteMatch(@PathVariable int match_id){
        return matchService.remove(match_id);
    }



}
