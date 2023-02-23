package com.project.finalcricketgame.service;

import com.project.finalcricketgame.dto.PlayerDTO;
import com.project.finalcricketgame.entities.Player;
import com.project.finalcricketgame.entities.PlayerTeamMapping;
import com.project.finalcricketgame.repository.MatchTeamMappingRepository;
import com.project.finalcricketgame.repository.PlayerTeamMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerTeamMapService {


    @Autowired
    PlayerTeamMappingRepository playerTeamMappingRepository;

    @Autowired
    PlayerService playerService;


    @Transactional
    public void remove(int playerID) {
        playerTeamMappingRepository.deleteAllByPlayerId(playerID);
    }

    public List<PlayerTeamMapping> findByPlayerID(int player_id) {
        return playerTeamMappingRepository.findByPlayerId(player_id);

    }

    public List<Player> findByTeam(String team_id) {
        ArrayList<Integer> ids = playerTeamMappingRepository.findByTeamId(team_id);
        return playerService.getPlayersByID(ids);
    }


    public void addPlayerToTeam(String teamName, int player_id) {
        PlayerTeamMapping playerTeamMapping = new PlayerTeamMapping();
        playerTeamMapping.setPlayer_id(player_id);
        playerTeamMapping.setTeam_id(teamName);
        playerTeamMappingRepository.save(playerTeamMapping);
    }

    @Transactional
    public void removePlayerFromTeam(int player_id) {
        playerTeamMappingRepository.deleteAllByPlayerId(player_id);
    }

    public int countOfPlayerinTeam(String team_id){
        return playerTeamMappingRepository.countByTeamId(team_id);
    }
}
