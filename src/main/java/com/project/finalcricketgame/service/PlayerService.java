package com.project.finalcricketgame.service;

import com.project.finalcricketgame.controller.MatchController;
import com.project.finalcricketgame.dto.PlayerDTO;
import com.project.finalcricketgame.entities.Player;
import com.project.finalcricketgame.repository.jpa.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private static final Logger logger = LoggerFactory.getLogger(MatchController.class);

    public String addPlayer(PlayerDTO playerDTO) {
        Player player = new Player(playerDTO);
        playerRepository.save(player);
        logger.info("Player added with id {}", player.getPlayer_id());
        return "Player added successfully with id : " + player.getPlayer_id();
    }

    public List<Player> getPlayersByID(ArrayList<Integer> ids) {
        return playerRepository.findAllById(ids);

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
            logger.warn("Player update request received, but Player {} doesn't exist", player_id);
            return ("Player with id : " + player_id + " doesn't exist");
        } else {
            Player updatedPlayer = player.get();
            if (Objects.equals(updatedPlayer.getName(), name)) {
                logger.warn("Player update request received, but Player {} name is already {}", player_id, name);
                return "Player name is already " + name;
            }
            updatedPlayer.setName(name);
            playerRepository.save(updatedPlayer);
            logger.info("Player {} name updated to {} ", player_id, name);
            return "Player name changed to " + name + " succesfully";
        }
    }

    // Player will become inactive, and will be removed from the team;
    public String deletePlayer(int player_id) {
        Optional<Player> player = findByisActive(player_id);
        if (player.isEmpty()) {
            logger.warn("Player delete request received, but Player {} doesn't exist", player_id);
            return ("Player with id : " + player_id + " doesn't exist");
        } else {
            Player playerToBeUpdated = player.get();
            playerTeamMapService.remove(player_id);
            playerToBeUpdated.setActive(false);
            playerRepository.save(playerToBeUpdated);
            logger.info("Player {} deleted", player_id);
            return "Player deleted Successfully";
        }
    }


}
