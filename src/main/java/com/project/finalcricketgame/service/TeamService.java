package com.project.finalcricketgame.service;

import com.project.finalcricketgame.controller.MatchController;
import com.project.finalcricketgame.dto.TeamDTO;
import com.project.finalcricketgame.entities.MatchTeamMapping;
import com.project.finalcricketgame.entities.Team;
import com.project.finalcricketgame.repository.jpa.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public String addTeam(TeamDTO teamDTO) {
        Team team = new Team(teamDTO);
        if (teamRepository.existsById(team.getName())) {
            logger.warn("Team creation request, but team already exist");
            return "Change Team name";
        }
        if (Objects.equals(team.getName(), "")) {
            logger.warn("Team creation request with empty name");
            return "TEAM NAME CAN'T BE EMPTY";
        }
        teamRepository.save(team);
        logger.info("Team {} created",team.getName());
        return "Team added Successfully";
    }

    public String findById(String team_id){
        if(teamRepository.findById(team_id).isEmpty()){
            return "";
        }
        return "OK";
    }

    private static final Logger logger = LoggerFactory.getLogger(MatchController.class);
    public Team findTeamById(String team_id){
        return teamRepository.findById(team_id).get();
    }

    public ArrayList<Team> getTeams() {
        return teamRepository.findTop8ByOrderByCreatedAtDesc();
    }

    public Long noOfTeams() {
        return teamRepository.count();
    }

    public String addPlayer(String teamName, int player_id) {
        if (teamRepository.findByname(teamName) == null) {
            logger.warn("Team Get Request received , But Team with team_id {} doesn't exist",teamName);
            return "Team " + teamName + " doesn't exist";
        }
        // ya to player id na ho ya active na ho
        if (playerService.findByisActive(player_id).isEmpty()) {
            logger.warn("Player addition to team request received, but Player {} doesn't exist",player_id);
            return "Player with player_id " + player_id + " doesn't exist";
        }
        if (playerTeamMapService.findByPlayerID(player_id).size() == 1) {
            logger.warn("Player addition to team request received, but Player {} is already in a team",player_id);
            return "Player is already in a team";
        }
        playerTeamMapService.addPlayerToTeam(teamName, player_id);
        logger.info("Player {} added to Team {}", player_id,teamName);
        return "Player added to Team successfully";
    }

    // player will be removed from the team, will remain active though
    // player team relation will be deleted;
    public String removePlayer(String teamName, int player_id) {
        if (teamRepository.findByname(teamName) == null) {
            logger.warn("Player removal from team request received , But Team with team_id {} doesn't exist",teamName);
            return "Team " + teamName + " doesn't exist";
        }
        if (playerService.findByisActive(player_id).isEmpty()) {
            logger.warn("Player removal from team request received, but Player {} doesn't exist",player_id);
            return "Player with player_id " + player_id + " doesn't exist";
        }
        if (playerTeamMapService.findByPlayerID(player_id).size() == 0) {
            logger.warn("Player removal from team request received, but Player {} not in the team",player_id);
            return "Player is not in the team";
        }
        playerTeamMapService.removePlayerFromTeam(player_id);
        logger.info("Player {} removed from Team {}", player_id,teamName);
        return "Player remove from Team successfully";
    }

    public boolean validTeam(String teamName){
        return teamRepository.findByname(teamName) != null;
    }
    public boolean TeamsValidity(int match_id){
        MatchTeamMapping matchTeamMapping = matchTeamMappingService.findByMatchId(match_id);
        if(matchTeamMapping == null)
            return false;
        String team1 = matchTeamMapping.getTeam1_id();
        String team2 = matchTeamMapping.getTeam2_id();
        return check(team1) && check(team2);
    }

    public boolean check(String team_id){
        return playerTeamMapService.countOfPlayerinTeam(team_id) >= 11;
    }


}
