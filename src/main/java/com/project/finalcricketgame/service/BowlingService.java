package com.project.finalcricketgame.service;

import com.project.finalcricketgame.entities.*;
import com.project.finalcricketgame.repository.jpa.BowlingStatsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import javax.ws.rs.NotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@Service
public class BowlingService {

    @Autowired
    BowlingStatsRepository bowlingStatsRepository;
    @Autowired
    TeamService teamService;
    @Autowired
    PlayerService playerService;

    private static final Logger logger = LoggerFactory.getLogger(BowlingService.class);

    public static Integer GetBowler(Integer prevBowler, HashMap<Integer, Integer> oversBowledByPlayers) {
        Random num = new Random();
        Integer  BowlerNumber = num.nextInt(11)+ 1;
        if (oversBowledByPlayers.containsKey(BowlerNumber)) {
            while (oversBowledByPlayers.get(BowlerNumber) >= 4 || BowlerNumber.equals(prevBowler)) {
                BowlerNumber = (BowlerNumber + num.nextInt(11)) % 11 + 1;
                if (!oversBowledByPlayers.containsKey(BowlerNumber)) {
                    break;
                }
            }
            if (oversBowledByPlayers.containsKey(BowlerNumber))
                oversBowledByPlayers.put(BowlerNumber, oversBowledByPlayers.get(BowlerNumber) + 1);
            else oversBowledByPlayers.put(BowlerNumber, 1);
        } else {
            oversBowledByPlayers.put(BowlerNumber, 1);
        }
        prevBowler = BowlerNumber;
        return prevBowler;
    }

    public static int getBall() {
        Random ball = new Random();
        return ball.nextInt(8);
    }

    int totalWickets(int match_id, String team_id) {
        return bowlingStatsRepository.findWicketsTakenByPlayerAndMatch(match_id, team_id);
    }

    ArrayList<BowlingStats> getStats(int match_id, String team_id) {
        ArrayList<BowlingStats> bowlingStats = new ArrayList<>();
        bowlingStats = bowlingStatsRepository.findBowlingStatsByPlayerAndMatch(match_id, team_id);
        return bowlingStats;
    }

    private ArrayList<BowlingStats> bowlingStatsList;

    @Autowired
    PlayerTeamMapService playerTeamMapService;

    void setTeam(Team bowlingTeam, Match match) {
        bowlingStatsList = new ArrayList<>();
        List<Player> playerList = new ArrayList<>();
        playerList = playerTeamMapService.findByTeam(bowlingTeam.getName());
        for (int i = 0; i < 11; i++) {
            bowlingStatsList.add(new BowlingStats(playerList.get(i), match, bowlingTeam.getName()));
        }
    }


    public void updateStats(int bowler_number, int score) {
        BowlingStats bowlingStats = bowlingStatsList.get(bowler_number - 1);
        if (score == 7) {
            bowlingStats.setWickets(bowlingStats.getWickets() + 1);
        } else {
            bowlingStats.setRuns(bowlingStats.getRuns() + score);
        }
        bowlingStats.setBalls(bowlingStats.getBalls() + 1);
        if (bowlingStats.getBalls() % 6 == 0) {
            bowlingStats.setOvers(bowlingStats.getOvers() + 1);
            bowlingStats.setBalls(0);
        }
    }

    public void save() {
        for (BowlingStats bowlingStats : bowlingStatsList) {
            bowlingStatsRepository.save(bowlingStats);
        }
    }

    public ResponseEntity<?> getBowlingStats(int playerId) {
        if (playerService.findByisActive(playerId).isEmpty()) {
            logger.warn("BowlingStats Request received , But player with playerId {} doesnt exist", playerId);
           throw new NotFoundException("Player with playerId: " + playerId + "Doesn't exist");
        }
        logger.info("BowlingStats Request received , BowlingStats  of Player {} fetched", playerId);
        Tuple tuple = bowlingStatsRepository.findByPlayer(playerId);
        Map<String, BigDecimal> result = new HashMap<>();
        result.put("overs", (BigDecimal) tuple.get("sum(overs)"));
        result.put("wickets", (BigDecimal) tuple.get("sum(wickets)"));
        result.put("matches", new BigDecimal((BigInteger) tuple.get("matches")));
        return ResponseEntity.ok(result);
    }



}
