package com.project.finalcricketgame.service;

import com.project.finalcricketgame.dto.TeamDTO;
import com.project.finalcricketgame.entities.MatchTeamMapping;
import com.project.finalcricketgame.entities.Player;
import com.project.finalcricketgame.entities.Team;
import com.project.finalcricketgame.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlReturnType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;


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
            return "Change Team name";
        }
        if (Objects.equals(team.getName(), "")) {
            return "TEAM NAME CAN'T BE EMPTY";
        }
        teamRepository.save(team);
        return "Team added Successfully";
    }

    public String findById(String team_id){
        if(teamRepository.findById(team_id).isEmpty()){
            return "";
        }
        return "OK";
    }

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
            return "Team " + teamName + " doesn't exist";
        }
        // ya to player id na ho ya active na ho
        if (playerService.findByisActive(player_id).isEmpty()) {
            return "Player with player_id " + player_id + " doesn't exist";
        }
        if (playerTeamMapService.findByPlayerID(player_id).size() == 1) {
            return "Player is already in a team";
        }
        playerTeamMapService.addPlayerToTeam(teamName, player_id);
        return "Player added to Team successfully";
    }

    // player will be removed from the team, will remain active though
    // player team relation will be deleted;
    public String removePlayer(String teamName, int player_id) {
        if (teamRepository.findByname(teamName) == null) {
            return "Team " + teamName + " doesn't exist";
        }
        if (playerService.findByisActive(player_id).isEmpty()) {
            return "Player with player_id " + player_id + " doesn't exist";
        }
        if (playerTeamMapService.findByPlayerID(player_id).size() == 0) {
            return "Player is not in the team";
        }
        playerTeamMapService.removePlayerFromTeam(player_id);
        return "Player remove from Team successfully";
    }

    public boolean validTeam(String teamName){
        if (teamRepository.findByname(teamName) == null) {
            return false;
        }
        return true;
    }
    public boolean TeamsValidity(int match_id){
        MatchTeamMapping matchTeamMapping = matchTeamMappingService.findByMatchId(match_id);
        String team1 = matchTeamMapping.getTeam1_id();
        String team2 = matchTeamMapping.getTeam2_id();
        return check(team1) && check(team2);
    }

    public boolean check(String team_id){
        return playerTeamMapService.countOfPlayerinTeam(team_id) >= 11;
    }


}
