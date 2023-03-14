package com.project.finalcricketgame.service;

import com.project.finalcricketgame.entities.BattingStats;
import com.project.finalcricketgame.entities.Match;
import com.project.finalcricketgame.entities.Player;
import com.project.finalcricketgame.entities.Team;
import com.project.finalcricketgame.repository.jpa.BattingStatsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import javax.ws.rs.NotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BattingService {
    @Autowired
    BattingStatsRepository battingStatsRepository;
    @Autowired
    PlayerTeamMapService playerTeamMapService;

    @Autowired
    PlayerService playerService;
    ArrayList<BattingStats> battingStatsList;
    private static final Logger logger = LoggerFactory.getLogger(BattingService.class);

    Integer totalRuns(int match_id, String team_id) {
        return battingStatsRepository.findRunsScoredByPlayerAndMatch(match_id, team_id);
    }

    ArrayList<BattingStats> getStats(int match_id, String team_id) {
        ArrayList<BattingStats> battingStats = new ArrayList<>();
        battingStats = battingStatsRepository.findBattingStatsByPlayerAndMatch(match_id, team_id);
        return battingStats;
    }

    void setTeam(Team battingTeam, Match match) {
        battingStatsList = new ArrayList<>();
        List<Player> playerList = new ArrayList<>();
        playerList = playerTeamMapService.findByTeam(battingTeam.getName());
        for (int i = 0; i < 11; i++) {
            battingStatsList.add(new BattingStats(playerList.get(i), match, battingTeam.getName()));
        }
    }

    public void updateStats(int batsman_number, int score) {
        BattingStats battingStats = battingStatsList.get(batsman_number - 1);
        if (score != 7)
            battingStats.setRunsScored(battingStats.getRunsScored() + score);
        battingStats.setBallsPlayed(battingStats.getBallsPlayed() + 1);
    }

    public void save() {
        for (BattingStats battingStats : battingStatsList) {
            battingStatsRepository.save(battingStats);
//            battingStatsRepositoryES.save(battingStats);
        }

    }

    public ResponseEntity<?> getBattingStats(int playerId) {
        if (playerService.findByisActive(playerId).isEmpty()) {
            logger.warn("BattingStats Request received , But player with playerId {} doesnt exist", playerId);
            throw new NotFoundException("Player with playerId: " + playerId + "Doesn't exist");
        }
        logger.info("BattingStats Request received , BattingStats  of Player {} fetched", playerId);
        Tuple tuple = battingStatsRepository.findByPlayer(playerId);
        Map<String, BigDecimal> result = new HashMap<>();
        result.put("balls_played", (BigDecimal) tuple.get("sum(balls_played)"));
        result.put("runs_scored", (BigDecimal) tuple.get("sum(runs_scored)"));
        result.put("matches", new BigDecimal((BigInteger) tuple.get("matches")));
        return ResponseEntity.ok(result);
    }

    // ELASTIC SEARCH FUNCTION

//    public Iterable<BattingStats> findAll() {
//        return battingStatsRepository.findAll();
//    }


}

