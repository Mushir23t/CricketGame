package com.project.finalcricketgame.service;

import com.project.finalcricketgame.entities.BattingStats;
import com.project.finalcricketgame.entities.Match;
import com.project.finalcricketgame.entities.Player;
import com.project.finalcricketgame.entities.Team;
import com.project.finalcricketgame.repository.BattingStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BattingService {
    @Autowired
    BattingStatsRepository battingStatsRepository;
    @Autowired
    PlayerTeamMapService playerTeamMapService;
    ArrayList<BattingStats> battingStatsList;


    Integer totalRuns(int match_id, String team_id) {
        return battingStatsRepository.findRunsScoredByPlayerAndMatch(match_id, team_id);
    }

    ArrayList<BattingStats> getStats(int match_id, String team_id) {
        ArrayList<BattingStats> battingStats = new ArrayList<>();
        battingStats = battingStatsRepository.findBattingStatsByPlayerAndMatch(match_id, team_id);
        return battingStats;
    }

    void set(Team battingTeam, Match match) {
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
        }
    }
}

