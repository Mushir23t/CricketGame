package org.cricketgame.service;

import org.cricketgame.dao.PlayerStatsRepository;
import org.cricketgame.dto.PlayerStats;
import org.cricketgame.model.Player;
import org.cricketgame.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PlayerStatsService {

    @Autowired
    private PlayerStatsRepository playerStatsRepository;

    public ResponseEntity<?> getPlayerStats(String playerName) {
        Optional<PlayerStats> playerStats = playerStatsRepository.findById(playerName);
        return ResponseEntity.ok(playerStats);
    }

    void addPlayersStats(Player player) {
        PlayerStats playerStats = new PlayerStats(player.getName(), 1, player.getRunsScored(), player.getWickets());
        playerStatsRepository.save(playerStats);
    }

    public void updatePlayersStats(UtilityForEachMatch.Pair<Team, Team> Teams) {
        Team Team1 = Teams.getFirst();
        Team Team2 = Teams.getSecond();
        ArrayList<Player> allplayersTeam1 = Team1.getPlayers();
        for (Player player : allplayersTeam1) {
            PlayerStats playerStats = playerStatsRepository.findById(player.getName()).orElse(null);
            if (playerStats != null) {
                playerStats.setMatchPlayed(playerStats.getMatchPlayed() + 1);
                playerStats.setRunsScored(playerStats.getRunsScored() + player.getRunsScored());
                playerStats.setWicketTaken(playerStats.getWicketTaken() + player.getWickets());
                playerStatsRepository.save(playerStats);
            } else {
                addPlayersStats(player);
            }
        }

        ArrayList<Player> allplayersTeam2 = Team2.getPlayers();
        for (Player player : allplayersTeam2) {
            PlayerStats playerStats = playerStatsRepository.findById(player.getName()).orElse(null);
            if (playerStats != null) {
                playerStats.setMatchPlayed(playerStats.getMatchPlayed() + 1);
                playerStats.setRunsScored(playerStats.getRunsScored() + player.getRunsScored());
                playerStats.setWicketTaken(playerStats.getWicketTaken() + player.getWickets());
                playerStatsRepository.save(playerStats);
            } else {
                addPlayersStats(player);
            }
        }
    }


}
