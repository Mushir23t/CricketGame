package com.project.finalcricketgame.service;

import com.project.finalcricketgame.entities.Match;
import com.project.finalcricketgame.entities.Player;
import com.project.finalcricketgame.entities.Team;
import com.project.finalcricketgame.entities.Tournament;
import com.project.finalcricketgame.repository.PlayerRepository;
import com.project.finalcricketgame.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

@Service
public class TournamentService {
    @Autowired
    private TeamService teamService;
    @Autowired
    private MatchService matchService;
    @Autowired
    private TournamentRepository tournamentRepository;
    private Tournament tournament;
    private HashMap<String, Integer> Table = new HashMap<>();
    private ArrayList<Team> playingTeams = new ArrayList<>();
    private ArrayList<Match> matches = new ArrayList<>();
    private String tableTopper = "";
    private int maxPoints = 0;

    public void createNewTournament() {
        tournament = new Tournament();
        System.out.println("ENTERED HERE");
        playingTeams = teamService.getTeams();
        System.out.println("PlayingTeamsSize " + playingTeams.size());
        tournament.setTeams(playingTeams);
//        playTournament();
    }

    // Start tournament for 8 teams (fixed) and (most Recent Teams added to database)
    // Else returns Response to add teams
    public ResponseEntity<?> startTournament() {
        Long noOfTeamsNeeded = 8 - teamService.noOfTeams();
        if (noOfTeamsNeeded >= 1) {
            return ResponseEntity.ok("ADD " + noOfTeamsNeeded + " Team" + (noOfTeamsNeeded >= 2 ? "s" :""));
        }
//        createNewTournament();
        return ResponseEntity.ok(tournament.getTournament_id() + tournament.getWinner());
    }

    // ROUND ROBIN FORMAT
    // 1 point for TIE (If matchWinner string is empty)
    // 2 point for winning
//    public void playTournament() {
//        int match_number = 0;
//        for(Team team : playingTeams){
//            System.out.println(team.getName());
//            for(Player player : team.getPlayers()){
//                System.out.println(player.getName());
//            }
//        }
//        for (int team1 = 0; team1 < 8; team1++) {
//            for (int team2 = team1 + 1; team2 < 8; team2++) {
//                match_number++;
//                int toss = (int) (Math.random()*2);
//                Match newMatch;
//                if (toss % 2 == 0) {
//                    newMatch = matchService.playMatch(playingTeams.get(team1), playingTeams.get(team2), match_number);
//                } else {
//                    newMatch = matchService.playMatch(playingTeams.get(team2), playingTeams.get(team1),match_number);
//                }
//                matches.add(newMatch);
//                String matchWinner = newMatch.getWinner();
//                String team1Name = getTeamName(team1);
//                String team2Name = getTeamName(team2);
//                if(Objects.equals(matchWinner, "")){
//                    updatePointsTable(team1Name, 1);
//                    updatePointsTable(team2Name, 1);
//                }
//                else{
//                    updatePointsTable(matchWinner,2);
//                }
//                save();
//            }
//        }
//    }
   private void updatePointsTable(String teamName, int points) {
        if (Table.containsKey(teamName)) {
            Table.put(teamName, Table.get(teamName) + points);
            if (maxPoints < Table.get(teamName)) ;
            {
                tableTopper = teamName;
                maxPoints = Table.get(teamName);
            }
        } else {
            Table.put(teamName, points);
            maxPoints = points;
        }
    }

    // This is used to save Every Entity and is under Every ServiceClass

    public void save() {
        tournament.setWinner(tableTopper);
        tournamentRepository.save(tournament);
        for (Match match : matches) {
//            matchService.updateTournament(match.getMatch_id(), tournament);
        }
    }

    public String getTeamName(int number){
        return playingTeams.get(number).getName();
    }
}
