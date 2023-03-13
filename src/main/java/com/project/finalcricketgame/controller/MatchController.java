package com.project.finalcricketgame.controller;

import com.project.finalcricketgame.dto.MatchDTO;
import com.project.finalcricketgame.entities.Match;
import com.project.finalcricketgame.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("CricketGame")
public class MatchController {
    @Autowired
    MatchService matchService;
    private static final Logger logger = LoggerFactory.getLogger(MatchController.class);

    @PostMapping("/Match/{team1}/{team2}")
    MatchDTO addMatch(@PathVariable String team1, @PathVariable String team2) {
        return matchService.addMatch(team1, team2);
    }
    @PutMapping("/Match/Simulate/{matchId}")
    String startMatch(@PathVariable int matchId) {
        return matchService.startMatch(matchId);
    }
    // soft deleting match
    @DeleteMapping("/Match/{matchId}")
    String deleteMatch(@PathVariable int matchId) {
        return matchService.remove(matchId);
    }

}
