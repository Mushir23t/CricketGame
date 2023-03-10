package com.project.finalcricketgame.service;

import com.project.finalcricketgame.entities.MatchTeamMapping;
import com.project.finalcricketgame.repository.jpa.MatchTeamMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchTeamMappingService {

    @Autowired
    MatchTeamMappingRepository matchTeamMappingRepository;

    public void createMapping(int match_id, String team1_id, String team2_id){
        MatchTeamMapping matchTeamMapping = new MatchTeamMapping(match_id,team1_id,team2_id);
        matchTeamMappingRepository.save(matchTeamMapping);
    }

    MatchTeamMapping findByMatchId(int match_id){
        return matchTeamMappingRepository.findBymatch_id(match_id);
    }
}
