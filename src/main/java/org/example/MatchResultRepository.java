package org.example;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MatchResultRepository extends MongoRepository<MatchResult,String> {
}
