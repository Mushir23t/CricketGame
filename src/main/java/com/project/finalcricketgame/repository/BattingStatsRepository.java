package com.project.finalcricketgame.repository;

import com.project.finalcricketgame.entities.BattingStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BattingStatsRepository extends JpaRepository<BattingStats,Integer> {

    @Query(value = " select sum(runs_scored) from batting_stats where match_id = :match_id and team_id = :team_id", nativeQuery = true)
    Integer findRunsScoredByPlayerAndMatch(int match_id, String team_id);

    @Query(value = " select * from batting_stats where match_id = :match_id and team_id = :team_id and balls_played > 0", nativeQuery = true)
    ArrayList<BattingStats> findBattingStatsByPlayerAndMatch(int match_id, String team_id);

}
