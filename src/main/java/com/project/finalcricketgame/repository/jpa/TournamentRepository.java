package com.project.finalcricketgame.repository.jpa;

import com.project.finalcricketgame.entities.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament,Integer> {
}
