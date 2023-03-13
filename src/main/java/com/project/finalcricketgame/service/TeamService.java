package com.project.finalcricketgame.service;

import com.project.finalcricketgame.dto.PlayerDTO;
import com.project.finalcricketgame.dto.TeamDTO;
import com.project.finalcricketgame.entities.MatchTeamMapping;
import com.project.finalcricketgame.entities.Player;
import com.project.finalcricketgame.entities.Team;
import com.project.finalcricketgame.repository.jpa.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class TeamService {
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    PlayerService playerService;
    @Autowired
    PlayerTeamMapService playerTeamMapService;
    @Autowired
    MatchTeamMappingService matchTeamMappingService;

    private static final Logger logger = LoggerFactory.getLogger(TeamService.class);

    public TeamDTO addTeam(TeamDTO teamDTO) {
        Team team = new Team(teamDTO);
        if (teamRepository.existsById(team.getName())) {
            logger.warn("Team creation request, but team already exist");
            throw new DuplicateKeyException("Team already exist");
        }
        if (Objects.equals(team.getName(), "")) {
            logger.warn("Team creation request with empty name");
            throw new BadRequestException("Team name is empty");
        }
        team = teamRepository.save(team);
        logger.info("Team {} created", team.getName());
        return teamDTO;
    }

    public List<PlayerDTO> getTeam(String teamId) {
        if (!teamExists(teamId)) {
            logger.warn("Team Get Request received , But Team with team_id {} doesn't exist", teamId);
            throw new NotFoundException("Team " + teamId + "Doesn't exist");
        }
        List<Player> players = playerTeamMapService.findByTeam(teamId);
        List<PlayerDTO> playersList = new ArrayList<>();
        for (Player player : players) {
            playersList.add(new PlayerDTO(player));
        }
        logger.info("Team details for team {} fetched", teamId);
        return playersList;
    }

    public Team findTeamById(String teamId) {
        return teamRepository.findById(teamId).get();
    }

    public String addPlayerToTeam(String teamName, int playerId) {
        if (teamRepository.findById(teamName).isEmpty()) {
            logger.warn("Team Get Request received , But Team with team_id {} doesn't exist", teamName);
            throw new NotFoundException("Team " + teamName + "Doesn't exist");
        }
        if (playerService.findByisActive(playerId).isEmpty()) {
            logger.warn("Player addition to team request received, but Player {} doesn't exist", playerId);
            throw new NotFoundException("Player with player_id: " + playerId + "Doesn't exist");
        }
        if (playerTeamMapService.findByPlayerID(playerId).size() == 1) {
            logger.warn("Player addition to team request received, but Player {} is already in a team", playerId);
            throw new IllegalStateException("Player is already in a team");
        }
        playerTeamMapService.addPlayerToTeam(teamName, playerId);
        logger.info("Player {} added to Team {}", playerId, teamName);
        return "Player added to Team successfully";
    }

    public String removePlayerFromTeam(String teamName, int playerId) {
        if (teamRepository.findById(teamName).isEmpty()) {
            logger.warn("Player removal from team request received , But Team with team_id {} doesn't exist", teamName);
            throw new NotFoundException("Team " + teamName + "Doesn't exist");
        }
        if (playerService.findByisActive(playerId).isEmpty()) {
            logger.warn("Player removal from team request received, but Player {} doesn't exist", playerId);
            throw new NotFoundException("Player with player_id: " + playerId + "Doesn't exist");
        }
        if (playerTeamMapService.findByPlayerID(playerId).size() == 0) {
            logger.warn("Player removal from team request received, but Player {} not in the team", playerId);
            throw new IllegalStateException("Player is not in a team");
        }
        playerTeamMapService.removePlayerFromTeam(playerId);
        logger.info("Player {} removed from Team {}", playerId, teamName);
        return "Player removed from Team successfully";
    }

    public boolean teamExists(String teamName) {
        return teamRepository.findById(teamName).isPresent();
    }

    public boolean teamsValidityForMatch(int matchId) {
        MatchTeamMapping matchTeamMapping = matchTeamMappingService.findByMatchId(matchId);
        if (matchTeamMapping == null) return false;
        String team1 = matchTeamMapping.getTeam1_id();
        String team2 = matchTeamMapping.getTeam2_id();
        return checkCountOfPlayerInTeam(team1) && checkCountOfPlayerInTeam(team2);
    }

    public boolean checkCountOfPlayerInTeam(String teamId) {
        return playerTeamMapService.countOfPlayerinTeam(teamId) >= 11;
    }


}
