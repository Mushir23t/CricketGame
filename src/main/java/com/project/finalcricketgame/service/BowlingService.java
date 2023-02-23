package com.project.finalcricketgame.service;

import com.project.finalcricketgame.entities.BowlingStats;
import com.project.finalcricketgame.entities.Match;
import com.project.finalcricketgame.entities.Player;
import com.project.finalcricketgame.entities.Team;
import com.project.finalcricketgame.repository.BowlingStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service
public class BowlingService {

    @Autowired
    BowlingStatsRepository bowlingStatsRepository;

    @Autowired
    TeamService teamService;

    public static Integer GetBowler(Integer prevBowler, HashMap<Integer, Integer> oversBowledByPlayers) {
        Random num = new Random();
        Integer BowlerNumber = num.nextInt(11) + 1;
        if (oversBowledByPlayers.containsKey(BowlerNumber)) {
            while (oversBowledByPlayers.get(BowlerNumber) > 4 || BowlerNumber.equals(prevBowler)) {
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

    void set(Team bowlingTeam, Match match) {
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


}
