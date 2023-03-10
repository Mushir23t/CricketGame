package com.project.finalcricketgame.repository.mongo;

import com.project.finalcricketgame.entities.ScoreCard;
import com.project.finalcricketgame.entities.ScoreCardMongo;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ScoreCardMongoRepository extends MongoRepository<ScoreCardMongo,String> {
    ScoreCardMongo findBymatchId(int matchID);
}
