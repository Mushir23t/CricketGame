package com.project.finalcricketgame.repository.jpa;

import com.project.finalcricketgame.entities.PlayerTeamMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PlayerTeamMappingRepository extends JpaRepository<PlayerTeamMapping,Integer> {

    @Modifying
    @Query("DELETE FROM PlayerTeamMapping ptm WHERE ptm.player_id = :playerId")
    void deleteAllByPlayerId(@Param("playerId") int playerId);

    @Query("Select ptm FROM PlayerTeamMapping ptm WHERE ptm.player_id = :playerId")
    List<PlayerTeamMapping> findByPlayerId(@Param("playerId") int playerId);

    @Query("Select ptm.player_id FROM PlayerTeamMapping ptm WHERE ptm.team_id= :teamId")
    ArrayList<Integer> findByTeamId(@Param("teamId") String teamId);

    @Query("SELECT COUNT(ptm.player_id) FROM PlayerTeamMapping ptm WHERE ptm.team_id = :teamId")
    int countByTeamId(@Param("teamId") String teamId);

}
