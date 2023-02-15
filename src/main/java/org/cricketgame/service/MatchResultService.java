package org.cricketgame.service;

import org.cricketgame.dao.MatchResultRepository;
import org.cricketgame.dto.MatchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatchResultService {

    public static int matchCount = 0;
    @Autowired
    private MatchResultRepository matchResultRepository;
    @Autowired
    private PlayerStatsService playerStatsService;

    public ResponseEntity<?> getMatchResult(Integer matchNumber) {
        Optional<MatchResult> matchResult = matchResultRepository.findById(matchNumber);
        return ResponseEntity.ok(matchResult);
    }


    public MatchResult addMatchResult() {
        matchCount++;
        UtilityForEachMatch utilityForEachMatch = new UtilityForEachMatch();
        String result = utilityForEachMatch.getMatchResultString();
        MatchResult matchResult = new MatchResult(result, matchCount);
        playerStatsService.updatePlayersStats(utilityForEachMatch.getTeams());
        return matchResultRepository.insert(matchResult);
    }

}
