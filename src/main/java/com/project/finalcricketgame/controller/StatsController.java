package com.project.finalcricketgame.controller;

import com.project.finalcricketgame.dto.ScoreCardDTO;
import com.project.finalcricketgame.service.MatchService;
import com.project.finalcricketgame.service.ScoreCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("CricketGame")
public class StatsController {

    @Autowired
    ScoreCardService scoreCardService;

    @Autowired
    MatchService matchService;

    @GetMapping("Match/{match_id}/Scorecard")
    public ResponseEntity<?> getScorecard(@PathVariable int match_id) {
        if(!matchService.isValidMatch(match_id)){
            return ResponseEntity.ok("Match not started yet or no match with match_id:" + match_id);
        }
       return ResponseEntity.ok(scoreCardService.getScoreCard(match_id));
    }
}
