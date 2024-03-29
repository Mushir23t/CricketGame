package com.project.finalcricketgame.repository.jpa;

import com.project.finalcricketgame.entities.Player;
import com.project.finalcricketgame.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {


}
