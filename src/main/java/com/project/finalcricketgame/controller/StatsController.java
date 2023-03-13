package com.project.finalcricketgame.controller;

import com.project.finalcricketgame.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Autowired
    PlayerService playerService;
    @Autowired
    BattingService battingService;
    @Autowired
    BowlingService bowlingService;
    private static final Logger logger = LoggerFactory.getLogger(StatsController.class);

    @GetMapping("Match/{match_id}/Scorecard/ES")
    public ResponseEntity<?> getScorecardFromES(@PathVariable int match_id) {
        if (!matchService.isValidMatch(match_id)) {
            logger.warn("Scorecard Request received , Match not started yet or no match with match_id {}", match_id);
            return ResponseEntity.ok("Match not started yet or no match with match_id:" + match_id);
        }
        logger.info("Scorecard Request received , Scorecard of Match {} fetched", match_id);
        return ResponseEntity.ok(scoreCardService.getScoreCardFromES(match_id));
    }

    @GetMapping("/Match/{match_id}/Scorecard")
    public ResponseEntity<?> getScorecardFromMongo(@PathVariable int match_id) {
        if (!matchService.isValidMatch(match_id)) {
            logger.warn("Scorecard Request received , Match not started yet or no match with match_id {}", match_id);
            return ResponseEntity.ok("Match not started yet or no match with match_id:" + match_id);
        }
        logger.info("Scorecard Request received , Scorecard of Match {} fetched", match_id);
        return ResponseEntity.ok(scoreCardService.getScoreCardFromMongo(match_id));
    }

    @GetMapping("/BattingStats/{player_id}")
    public ResponseEntity<?> getBattingStats(@PathVariable int player_id) {
        if (playerService.findByisActive(player_id).isEmpty()) {
            logger.warn("BattingStats Request received , But player with player_id {} doesnt exist", player_id);
            return ResponseEntity.ok("Player doesn't exist");
        }
        logger.info("BattingStats Request received , BattingStats  of Player {} fetched", player_id);
        return battingService.getBattingStats(player_id);
    }


    @GetMapping("/BowlingStats/{player_id}")
    public ResponseEntity<?> getBowlingStats(@PathVariable int player_id) {
        if (playerService.findByisActive(player_id).isEmpty()) {
            logger.warn("BowlingStats Request received , But player with player_id {} doesnt exist", player_id);
            return ResponseEntity.ok("Player doesn't exist");
        }
        logger.info("BowlingStats Request received , BowlingStats  of Player {} fetched", player_id);
        return bowlingService.getBowlingStats(player_id);
    }
}
