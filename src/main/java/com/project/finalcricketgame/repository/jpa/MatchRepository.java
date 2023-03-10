package com.project.finalcricketgame.repository.jpa;

import com.project.finalcricketgame.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match,Integer > {
    Match findById(int id);
}
