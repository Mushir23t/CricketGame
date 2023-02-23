package com.project.finalcricketgame.service;

import com.project.finalcricketgame.entities.Match;
import com.project.finalcricketgame.entities.MatchTeamMapping;
import com.project.finalcricketgame.entities.Team;
import com.project.finalcricketgame.repository.MatchRepository;
import com.project.finalcricketgame.repository.MatchTeamMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MatchService {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    MatchTeamMappingService matchTeamMappingService;

    @Autowired
    InningsService inningsService;

    @Autowired
    TeamService teamService;
    private int firstInningsTotal;
    private int secondInningsTotal;
    @Autowired
    private MatchTeamMappingRepository matchTeamMappingRepository;

    public boolean getMatch(int match_id) {
        Match match = matchRepository.findById(match_id);
        return match != null && !match.isDeleted();
    }

    public Match findMatch(int match_id){
        return matchRepository.findById(match_id);
    }

    public int createMatch(String team1, String team2) {

        Match match = new Match();
        matchRepository.save(match);
        int match_id = match.getMatch_id();
        matchTeamMappingService.createMapping(match_id, team1, team2);
        return match.getMatch_id();
    }


    public void playMatch(int match_id) {
        MatchTeamMapping matchTeamMapping = matchTeamMappingService.findByMatchId(match_id);
        Match match = matchRepository.findById(match_id);
        Team team1 = teamService.findTeamById(matchTeamMapping.getTeam1_id());
        Team team2 = teamService.findTeamById(matchTeamMapping.getTeam2_id());
        System.out.println("First Batting Team is " + team1.getName());
        inningsService.initialiseInnings(1, team1, team2, match);
        inningsService.beginInnings(Integer.MAX_VALUE);
        firstInningsTotal = inningsService.getRuns();
        System.out.println("Target is " + firstInningsTotal);
        inningsService.initialiseInnings(2, team2, team1, match);
        inningsService.beginInnings(firstInningsTotal + 1);
        secondInningsTotal = inningsService.getRuns();
        System.out.println("Second team scored" + inningsService.getRuns() + "/" + inningsService.getWickets());
        updateMatchWinner(team1, team2, match.getMatch_id());
    }

    private void updateMatchWinner(Team team1, Team team2, int id) {
        Match match = matchRepository.findById(id);
        if (Objects.equals(firstInningsTotal, secondInningsTotal)) {
            match.setWinner("");
        } else if (firstInningsTotal > secondInningsTotal) {
            match.setWinner(team1.getName());
        } else {
            match.setWinner(team2.getName());
        }
        match.setStatus("Finished");
        matchRepository.save(match);
    }

    public boolean check(int match_id) {
        if(!isValidMatch(match_id)){
            return false;
        }
        Match match = matchRepository.findById(match_id);
        if (Objects.equals(match.getStatus(), "Finished")) {
            return false;
        }
        return true;
    }

    public boolean isValidMatch(int match_id){
        Match match =  matchRepository.findById(match_id);
        return match != null && !match.isDeleted();
    }

    public void save(Match match) {
        matchRepository.save(match);
    }


    public String remove(int match_id) {
        Match match = matchRepository.findById(match_id);
        if (match == null) {
            return "No such match with match_id : " + match_id;
        } else {
            match.setDeleted(true);
            matchRepository.save(match);
            return "Deleted successfully";
        }
    }

}
