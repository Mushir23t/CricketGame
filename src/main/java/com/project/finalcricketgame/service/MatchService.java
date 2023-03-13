package com.project.finalcricketgame.service;

import com.project.finalcricketgame.dto.MatchDTO;
import com.project.finalcricketgame.entities.Match;
import com.project.finalcricketgame.entities.MatchTeamMapping;
import com.project.finalcricketgame.entities.Team;
import com.project.finalcricketgame.repository.jpa.MatchRepository;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
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

    private static final Logger logger = LoggerFactory.getLogger(MatchService.class);


    public boolean getMatch(int matchId) {
        Match match = matchRepository.findById(matchId);
        return match != null && !match.isDeleted();
    }

    public MatchDTO addMatch(String team1, String team2) {
        boolean teamExist = teamService.teamExists(team1);
        if (!teamExist) {
            logger.warn("Match creation request received, but  Team {} doesn't exist", team1);
            throw new NotFoundException("Team " + team1 + "Doesn't exist");
        }
        teamExist = teamService.teamExists(team2);
        if (!teamExist) {
            logger.warn("Match creation request received, but Team {}  doesn't exist", team2);
            throw new NotFoundException("Team " + team2 + "Doesn't exist");
        }
        logger.info("Match is created");
        return Match.toDTO(createMatch(team1, team2));
    }

    public String startMatch(int matchId) {
        if (!canBeSimulated(matchId)) {
            logger.error("Attempted to run match with id: {}, Match already played or no match with matchId: {}", matchId, matchId);
            throw new RuntimeException("Match already played or no match with matchId:" + matchId);
        }
        boolean teamCheck = teamService.teamsValidityForMatch(matchId);
        if (!teamCheck) {
            logger.warn("Match running request received, but one of the Team doesn't have 11 players");
            throw new RuntimeException("Both team must have 11 players");
        }
        logger.info("Match with id {}, started ", matchId);
        playMatch(matchId);
        return "Match Completed";
    }

    public Match findMatch(int match_id) {
        return matchRepository.findById(match_id);
    }

    public Match createMatch(String team1, String team2) {
        Match match = new Match();
        match = matchRepository.save(match);
        int matchId = match.getMatch_id();
        matchTeamMappingService.createMapping(matchId, team1, team2);
        return match;
    }


    public void playMatch(int match_id) {
        MatchTeamMapping matchTeamMapping = matchTeamMappingService.findByMatchId(match_id);
        Match match = matchRepository.findById(match_id);
        Team team1 = teamService.findTeamById(matchTeamMapping.getTeam1_id());
        Team team2 = teamService.findTeamById(matchTeamMapping.getTeam2_id());

        // Initializing Innings
        inningsService.initialiseInnings(1, team1, team2, match);

        // Simulating Innings 1 (target set to INT_MAX)
        inningsService.beginInnings(Integer.MAX_VALUE);

        firstInningsTotal = inningsService.getRuns();
        inningsService.initialiseInnings(2, team2, team1, match);

        // Simulating Innings2
        inningsService.beginInnings(firstInningsTotal + 1);
        secondInningsTotal = inningsService.getRuns();

        // updating Winner of match
        updateMatchWinner(team1, team2, match.getMatch_id());

        // Generating ScoreCard
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

    public boolean canBeSimulated(int match_id) {
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

    public String remove(int matchId) {
        Match match = matchRepository.findById(matchId);
        if (match == null) {
            logger.error("Attempted to delete match with invalid id {}", matchId);
            throw new RuntimeException("No match with matchId:" + matchId);
        } else {
            match.setDeleted(true);
            match = matchRepository.save(match);
            logger.info("Match with id {} deleted", matchId);
            return "Deleted successfully";
        }
    }


}
