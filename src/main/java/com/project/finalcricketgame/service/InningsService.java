package com.project.finalcricketgame.service;

import com.project.finalcricketgame.entities.Innings;
import com.project.finalcricketgame.entities.Match;
import com.project.finalcricketgame.entities.Overs;
import com.project.finalcricketgame.entities.Team;
import com.project.finalcricketgame.repository.InningsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class InningsService {
    @Autowired
    InningsRepository inningsRepository;
    @Autowired
    BowlingService bowlingService;
    @Autowired
    BattingService battingService;
    @Autowired
    OversService oversService;
    private Team battingTeam;
    private Team bowlingTeam;
    Innings inningsObject1, inningsObject2;
    private HashMap<Integer, Integer> OversBowledByPlayers;
    ArrayList<Overs> oversList;
    private int runs, wickets, bowlerNumber, onStrikeBatsman, nonStrikeBatsman;
    private boolean inningsEnded;

    public int getRuns() {
        return runs;
    }

    public int getWickets() {
        return wickets;
    }

    InningsService() {

    }

    private int inningsNumber;


    void initialiseInnings(int inningsNumber, Team battingTeam, Team bowlingTeam, Match match) {
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.inningsNumber = inningsNumber;
        runs = 0;
        wickets = 0;
        bowlerNumber = -1;
        onStrikeBatsman = 1;
        nonStrikeBatsman = 2;
        inningsEnded = false;
        oversList = new ArrayList<>();
        OversBowledByPlayers = new HashMap<>();
        if (inningsNumber == 1)
            inningsObject1 = new Innings(inningsNumber, battingTeam.getName(), bowlingTeam.getName(), match);
        else
            inningsObject2 = new Innings(inningsNumber, battingTeam.getName(), bowlingTeam.getName(), match);
        bowlingService.set(bowlingTeam, match);
        battingService.set(battingTeam, match);
    }

    public void beginInnings(int target) {
        for (int overNumber = 1; overNumber <= 20; overNumber++) {
            bowlerNumber = BowlingService.GetBowler(bowlerNumber, OversBowledByPlayers);
            Overs curOver = new Overs();
            curOver.setOver_number(overNumber);
            int ballsBowled = beginOver(target, curOver);
            if (overNumber == 20 && ballsBowled == 6) inningsEnded = true;
            if (inningsEnded) {
                save();
                break;
            }
            // change Strike
            int temp = onStrikeBatsman;
            onStrikeBatsman = nonStrikeBatsman;
            nonStrikeBatsman = temp;
            oversList.add(curOver);
        }
    }

    public int beginOver(int target, Overs curOver) {
        int BallsBowled;
        for (BallsBowled = 1; BallsBowled <= 6; BallsBowled++) {
            int score = BowlingService.getBall();
            curOver.addBalls(score);
            updateStats(score);
            if (runs >= target || wickets == 10) {
                inningsEnded = true;
                System.out.println("INnings over");
                break;
            }
        }
        oversService.save(curOver);
        return BallsBowled;
    }

    private void updateStats(Integer score) {
        bowlingService.updateStats(bowlerNumber, score);
        battingService.updateStats(onStrikeBatsman, score);
        if (score == 7) {
            onStrikeBatsman = Math.max(onStrikeBatsman, nonStrikeBatsman) + 1;
            wickets++;
        }
        else
            runs += score;
        if (score % 2 == 1) {
            int temp = onStrikeBatsman;
            onStrikeBatsman = nonStrikeBatsman;
            nonStrikeBatsman = temp;
        }
    }

    // setting foreign key for innings in over table
    void updateOvers() {
        for (Overs overs : oversList) {
            if (inningsNumber == 1)
                oversService.updateInnings(overs.getOvers_id(), inningsObject1);
            else
                oversService.updateInnings(overs.getOvers_id(), inningsObject2);
        }
    }

    public void save() {
        if (inningsNumber == 1)
            inningsRepository.save(inningsObject1);
        else
            inningsRepository.save(inningsObject2);
        System.out.println(runs);
        System.out.println("Between two innings");
        updateOvers();
        battingService.save();
        bowlingService.save();
    }
}
