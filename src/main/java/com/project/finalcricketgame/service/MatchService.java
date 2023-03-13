package com.project.finalcricketgame.service;

import com.project.finalcricketgame.entities.Match;
import com.project.finalcricketgame.entities.MatchTeamMapping;
import com.project.finalcricketgame.entities.Team;
import com.project.finalcricketgame.repository.jpa.MatchRepository;
import com.project.finalcricketgame.repository.jpa.MatchTeamMappingRepository;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Getter
@Setter
public class MatchService {

    @Autowired
    MatchRepository matchRepository;
    @Autowired
    MatchTeamMappingService matchTeamMappingService;
    @Autowired
    InningsService inningsService;
    @Autowired
    TeamService teamService;
    @Autowired
    ScoreCardService scoreCardService;
    private int firstInningsTotal;
    private int secondInningsTotal;
    @Autowired
    private MatchTeamMappingRepository matchTeamMappingRepository;

    private static final Logger logger = LoggerFactory.getLogger(MatchService.class);


    public boolean getMatch(int match_id) {
        Match match = matchRepository.findById(match_id);
        return match != null && !match.isDeleted();
    }


    public Match findMatch(int match_id) {
        return matchRepository.findById(match_id);
    }

    public int createMatch(String team1, String team2) {





        Match match = new Match();
        match = matchRepository.save(match);
        int match_id = match.getMatch_id();
        matchTeamMappingService.createMapping(match_id, team1, team2);
        return match.getMatch_id();
    }


    public void playMatch(int match_id) {



        MatchTeamMapping matchTeamMapping = matchTeamMappingService.findByMatchId(match_id);
        Match match = matchRepository.findById(match_id);
        Team team1 = teamService.findTeamById(matchTeamMapping.getTeam1_id());
        Team team2 = teamService.findTeamById(matchTeamMapping.getTeam2_id());

        //Inning 2 simukted
        inningsService.initialiseInnings(1, team1, team2, match);
        logger.debug("First innings started with batting Team {}", team1.getName());
        inningsService.beginInnings(Integer.MAX_VALUE);
        firstInningsTotal = inningsService.getRuns();
        logger.debug("Team {} scored {}/{}", team1.getName(), firstInningsTotal, inningsService.getWickets());



        inningsService.initialiseInnings(2, team2, team1, match);
        inningsService.beginInnings(firstInningsTotal + 1);
        secondInningsTotal = inningsService.getRuns();
        logger.debug("Team  {} scored {} / {}", team2.getName(), inningsService.getRuns(), inningsService.getWickets());


        updateMatchWinner(team1, team2, match.getMatch_id());
        logger.debug("Team {} won", match.getWinner());
        scoreCardService.createScoreCard(match_id);
    }

    public String updateMatchWinner(Team team1, Team team2, int id) {
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
        return "LOL";
    }

    public boolean check(int match_id) {
        Match match = matchRepository.findById(match_id);
        if (match == null || match.isDeleted()) {
            return false;
        }
        return !Objects.equals(match.getStatus(), "Finished");
    }

    public boolean isValidMatch(int match_id) {
        Match match = matchRepository.findById(match_id);
        if (match == null) {
            return false;
        }
        if (match.isDeleted()) {
            return false;
        }
        return !Objects.equals(match.getStatus(), "Not started yet");
    }

    public void save(Match match) {
        matchRepository.save(match);
    }


    public String remove(int match_id) {
        Match match = matchRepository.findById(match_id);
        if (match == null) {
            logger.error("Attempted to delete match with invalid id {}", match_id);
            return "No such match with match_id : " + match_id;
        } else {
            match.setDeleted(true);
            matchRepository.save(match);
            logger.info("Match with id {} deleted", match_id);
            return "Deleted successfully";
        }
    }


}
