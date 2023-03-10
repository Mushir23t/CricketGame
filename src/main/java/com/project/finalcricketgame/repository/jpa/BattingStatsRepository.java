package com.project.finalcricketgame.repository.jpa;

import com.project.finalcricketgame.entities.BattingStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.ArrayList;

@Repository
public interface BattingStatsRepository extends JpaRepository<BattingStats,Integer> {

    @Query(value = " select sum(runs_scored) from batting_stats where match_id = :match_id and team_id = :team_id", nativeQuery = true)
    Integer findRunsScoredByPlayerAndMatch(int match_id, String team_id);

    @Query(value = " select * from batting_stats where match_id = :match_id and team_id = :team_id and balls_played > 0", nativeQuery = true)
    ArrayList<BattingStats> findBattingStatsByPlayerAndMatch(int match_id, String team_id);

    @Query(value = "select sum(balls_played),sum(runs_scored), count(match_id) as matches from batting_stats where player_id = :player_id ", nativeQuery = true)
    Tuple findByPlayer(int player_id);

}
