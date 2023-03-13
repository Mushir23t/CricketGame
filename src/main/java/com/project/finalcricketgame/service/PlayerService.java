package com.project.finalcricketgame.service;

import com.project.finalcricketgame.dto.CreatePlayerDTO;
import com.project.finalcricketgame.dto.PlayerDTO;
import com.project.finalcricketgame.entities.Player;
import com.project.finalcricketgame.repository.jpa.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.NoContentException;
import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.sun.xml.bind.v2.schemagen.Util.equalsIgnoreCase;

@Service
public class PlayerService {
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    PlayerTeamMapService playerTeamMapService;
    private static final Logger logger = LoggerFactory.getLogger(PlayerService.class);

    public PlayerDTO addPlayer(CreatePlayerDTO createPlayerDTO) throws ValidationException {
        String name = createPlayerDTO.getName();
        if (name == null || name.isEmpty()) {
            throw new ValidationException("Player name must be non-empty.");
        }
        Player player = new Player(createPlayerDTO);
        player = playerRepository.save(player);
        logger.info("Player added with id {}", player.getPlayer_id());
        return new PlayerDTO(player);
    }

    public List<Player> getPlayersByID(ArrayList<Integer> ids) {
        return playerRepository.findAllById(ids);
    }

    public List<PlayerDTO> getPlayers() throws NoContentException {
        List<Player> players = playerRepository.findByisActive(true);
        List<PlayerDTO> playersList = new ArrayList<>();
        for (Player player : players) {
            playersList.add(new PlayerDTO(player));
        }
        if (playersList.isEmpty()) {
            throw new NoContentException("No players added");
        }
        return playersList;
    }

    public Optional<Player> findByisActive(int playerId) {
        return playerRepository.isActiveByPlayerId(playerId);
    }

    public PlayerDTO updatePlayer(int playerId, CreatePlayerDTO playerDTO) throws ValidationException {
        String name = playerDTO.getName();
        Optional<Player> optionalPlayer = findByisActive(playerId);
        if (optionalPlayer.isEmpty()) {
            logger.warn("Player update request received, but Player {} doesn't exist", playerId);
            throw new NotFoundException("Player doesn't exist");
        } else if (name == null || name.isEmpty()) {
            throw new ValidationException("Player name must be non-empty.");
        } else {
            Player playerToBeUpdated = optionalPlayer.get();
            if (equalsIgnoreCase(playerToBeUpdated.getName(), name)) {
                logger.warn("Player update request received, but Player {} name is already {}", playerId, name);
                throw new BadRequestException("Player name is already" + name);
            }
            playerToBeUpdated.setName(name);
            playerToBeUpdated = playerRepository.save(playerToBeUpdated);
            logger.info("Player {} name updated to {} ", playerId, name);
            return new PlayerDTO(playerToBeUpdated);
        }
    }

    // Player will become inactive, and will be removed from the team;
    public String deletePlayer(int playerId) {
        Optional<Player> player = findByisActive(playerId);
        if (player.isEmpty()) {
            logger.warn("Player delete request received, but Player {} doesn't exist", playerId);
            throw new NotFoundException("Player doesn't exist");
        } else {
            Player playerToBeUpdated = player.get();
            playerTeamMapService.remove(playerId);
            playerToBeUpdated.setActive(false);
            playerRepository.save(playerToBeUpdated);
            logger.info("Player {} deleted", playerId);
            return "Player deleted Successfully";
        }
    }
}
