package org.cricketgame.controller;

import org.cricketgame.service.MatchResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@RestController
public class MatchResultController {

    @Autowired
    private MatchResultService matchResultService;

    @GetMapping("/Match/{matchNumber}")
    @ResponseBody
    public ResponseEntity<?> getMatchResult(@PathVariable Integer matchNumber) {
        return ResponseEntity.ok(matchResultService.getMatchResult(matchNumber));
    }

    @PostMapping("/Match")
    public ResponseEntity<?> addMatch() {
        return ResponseEntity.ok(matchResultService.addMatchResult());
    }
}