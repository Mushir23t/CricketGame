package com.project.finalcricketgame.repository.jpa;

import com.project.finalcricketgame.entities.BowlingStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.ArrayList;

@Repository
public interface BowlingStatsRepository extends JpaRepository<BowlingStats, Integer> {
    @Query(value = " select * from bowling_stats where match_id = :match_id and team_id = :team_id and overs ", nativeQuery = true)
    ArrayList<BowlingStats> findBowlingStatsByPlayerAndMatch(int match_id, String team_id);

    @Query(value = " select sum(wickets) from bowling_stats where match_id = :match_id and team_id = :team_id and overs + balls > 0", nativeQuery = true)
    Integer findWicketsTakenByPlayerAndMatch(int match_id, String team_id);


    @Query(value = "select  sum(overs), sum(wickets), count(match_id) as matches from bowling_stats where player_id = :player_id ", nativeQuery = true)
    Tuple findByPlayer(int player_id);

}
