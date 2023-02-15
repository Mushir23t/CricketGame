package org.cricketgame.dao;

import org.cricketgame.dto.PlayerStats;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerStatsRepository extends MongoRepository<PlayerStats, String> {

}
