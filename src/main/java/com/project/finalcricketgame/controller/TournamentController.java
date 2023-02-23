package com.project.finalcricketgame.controller;

import com.project.finalcricketgame.entities.Tournament;
import com.project.finalcricketgame.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("CricketGame")
public class TournamentController {

    @Autowired
    TournamentService tournamentService;

    @PostMapping("/StartTournament")
    public ResponseEntity<?> startTournament(){
        return tournamentService.startTournament();
    }
}
