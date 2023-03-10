package com.project.finalcricketgame.repository.es;
import com.project.finalcricketgame.entities.ScoreCard;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScoreCardESRepository extends ElasticsearchRepository<ScoreCard, String> {
    @Query("{\"match\": {\"MatchId\": {\"query\": \"?0\"}}}")
    Optional<ScoreCard> findByMatchId(int matchId);
}
