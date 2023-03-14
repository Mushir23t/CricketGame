package com.project.finalcricketgame.controller;

import com.project.finalcricketgame.dto.ScoreCardDTO;
import com.project.finalcricketgame.service.BattingService;
import com.project.finalcricketgame.service.BowlingService;
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
    BattingService battingService;
    @Autowired
    BowlingService bowlingService;

    @GetMapping("Match/{match_id}/Scorecard/ES")
    public ScoreCardDTO getScorecardFromES(@PathVariable int match_id) {
        return scoreCardService.getScoreCardFromES(match_id);
    }

    @GetMapping("/Match/{match_id}/Scorecard")
    public ResponseEntity<?> getScorecardFromMongo(@PathVariable int match_id) {
        return ResponseEntity.ok(scoreCardService.getScoreCardFromMongo(match_id));
    }

    @GetMapping("/BattingStats/{player_id}")
    public ResponseEntity<?> getBattingStats(@PathVariable int player_id) {
        return battingService.getBattingStats(player_id);
    }

    @GetMapping("/BowlingStats/{player_id}")
    public ResponseEntity<?> getBowlingStats(@PathVariable int player_id) {
        return bowlingService.getBowlingStats(player_id);
    }
}
