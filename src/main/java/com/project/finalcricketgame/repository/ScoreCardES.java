package com.project.finalcricketgame.repository;
import com.project.finalcricketgame.dto.ScoreCardDTO;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScoreCardES extends ElasticsearchRepository<ScoreCardDTO, String> {
    @Query("{\"match\": {\"MatchId\": {\"query\": \"?0\"}}}")
    Optional<ScoreCardDTO> findByMatchId(int matchId);
}
