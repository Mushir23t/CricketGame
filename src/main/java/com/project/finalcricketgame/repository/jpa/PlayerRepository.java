package com.project.finalcricketgame.repository.jpa;

import com.project.finalcricketgame.entities.Player;
import com.project.finalcricketgame.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    Player findByName(String name);

    List<Player> findByisActive(boolean isActive);

    @Query("Select p FROM Player p WHERE p.player_id = :playerId and p.isActive = true")
    Optional<Player> isActiveByPlayerId(@Param("playerId") int playerId);

}
