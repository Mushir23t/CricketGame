package com.project.finalcricketgame.repository.es;
import com.project.finalcricketgame.entities.ScoreCardES;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScoreCardESRepository extends ElasticsearchRepository<ScoreCardES, String> {
    @Query("{\"match\": {\"MatchId\": {\"query\": \"?0\"}}}")
    Optional<ScoreCardES> findByMatchId(int matchId);
}
