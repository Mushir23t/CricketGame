package com.project.finalcricketgame.service;

import com.project.finalcricketgame.dto.PlayerDTO;
import com.project.finalcricketgame.entities.Player;
import com.project.finalcricketgame.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    PlayerTeamMapService playerTeamMapService;

    public String addPlayer(PlayerDTO playerDTO) {
        Player player = new Player(playerDTO);
        playerRepository.save(player);
        return "Player added successfully";
    }

    public List<Player> getPlayersByID(ArrayList<Integer> ids){
        return  playerRepository.findAllById(ids);

    }

    public List<PlayerDTO> getPlayers() {
        List<Player> players = playerRepository.findByisActive(true);
        List<PlayerDTO> playerDTOS = new ArrayList<>();
        for (Player player : players) {
            playerDTOS.add(new PlayerDTO(player));
        }
        return playerDTOS;
    }


    public Optional<Player> findByisActive(int player_id) {
        return playerRepository.isActiveByPlayerId(player_id);
    }

    public String updatePlayer(int player_id, String name) {
        Optional<Player> player = findByisActive(player_id);
        if (player.isEmpty()) {
            return ("Player with id : " + player_id + " doesn't exist");
        } else {
            Player updatedPlayer = player.get();
            if (Objects.equals(updatedPlayer.getName(), name)) {
                return "Player name is already " + name;
            }
            updatedPlayer.setName(name);
            playerRepository.save(updatedPlayer);
            return "Player name changed to " + name + " succesfully";
        }
    }

    // Player will become inactive, and will be removed from the team;
    public String deletePlayer(int player_id) {
        Optional<Player> player = findByisActive(player_id);
        if (player.isEmpty()) {
            return ("Player with id : " + player_id + " doesn't exist");
        } else {
            Player playerToBeUpdated = player.get();
            playerTeamMapService.remove(player_id);
            playerToBeUpdated.setActive(false);
            return "Player deleted Successfully";
        }
    }


}
