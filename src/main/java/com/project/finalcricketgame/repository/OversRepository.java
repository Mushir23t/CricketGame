package com.project.finalcricketgame.repository;

import com.project.finalcricketgame.entities.Overs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OversRepository extends JpaRepository<Overs,Integer> {

}
