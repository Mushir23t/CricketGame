package org.cricketgame.dao;

import org.cricketgame.dto.MatchResult;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchResultRepository extends MongoRepository<MatchResult, Integer> {

}
