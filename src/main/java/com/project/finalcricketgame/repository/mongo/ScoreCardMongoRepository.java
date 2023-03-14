package com.project.finalcricketgame.repository.mongo;

import com.project.finalcricketgame.entities.ScoreCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ScoreCardMongoRepository extends MongoRepository<ScoreCard,String> {
    ScoreCard findBymatchId(int matchID);
}
