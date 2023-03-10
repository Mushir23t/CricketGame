package com.project.finalcricketgame.repository.jpa;

import com.project.finalcricketgame.entities.MatchTeamMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatchTeamMappingRepository extends JpaRepository<MatchTeamMapping,Integer> {

    @Query("select mtm from MatchTeamMapping mtm where mtm.match_id = :match_id")
    MatchTeamMapping findBymatch_id(@Param("match_id") int match_id);
}
