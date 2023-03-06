package com.project.finalcricketgame.repository;
import com.project.finalcricketgame.dto.ScoreCardDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScoreCardES extends ElasticsearchRepository<ScoreCardDTO, Integer> {

    Optional<ScoreCardDTO> findByMatchId(int match_id);
}
