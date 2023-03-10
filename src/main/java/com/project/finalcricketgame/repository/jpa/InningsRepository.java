package com.project.finalcricketgame.repository.jpa;

import com.project.finalcricketgame.entities.Innings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InningsRepository extends JpaRepository<Innings,Integer> {
}
