package com.project.finalcricketgame.controller;

import com.project.finalcricketgame.service.MatchService;
import com.project.finalcricketgame.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("CricketGame")
public class MatchController {

    @Autowired
    MatchService matchService;
    @Autowired
    TeamService teamService;
    private static final Logger logger = LoggerFactory.getLogger(MatchController.class);

    @PostMapping("/CreateMatch/{team1}/{team2}")
    String addMatch(@PathVariable String team1, @PathVariable String team2) {
        boolean teamExist = teamService.validTeam(team1);
        if(!teamExist){
            logger.warn("Match creation request received, but  Team {} doesn't exist",team1);
            return "Team " + team1 + " doesn't exist";

        }
        teamExist = teamService.validTeam(team2);
        if(!teamExist){
            logger.warn("Match creation request received, but Team {}  doesn't exist",team2);
            return "Team " + team2 + " doesn't exist";
        }
        int match_id = matchService.createMatch(team1, team2);
        logger.info("Match is created with match_id: {}", match_id);
        return "Match id is " + match_id;
    }

    @PostMapping("/StartMatch/{match_id}")
    String startMatch(@PathVariable int match_id) {
        if(!matchService.check(match_id)){
            logger.error("Attempted to run match with id: {}, Match already played or no match with match_id: {}", match_id, match_id);
            return "Match already played or no match with match_id:" + match_id;
        }
        boolean teamCheck = teamService.TeamsValidity(match_id);
        if(!teamCheck){
            logger.warn("Match running request received, but one of the Team doesn't have 11 players");
            return "Both team must have 11 players";
        }
        matchService.playMatch(match_id);
        logger.info("Match with id {}, started ", match_id);
        return "Match Completed";
    }

    @DeleteMapping("/DeleteMatch/{match_id}")
    String deleteMatch(@PathVariable int match_id){
        return matchService.remove(match_id);
    }



}
