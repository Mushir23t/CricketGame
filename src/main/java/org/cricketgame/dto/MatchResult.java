package org.cricketgame.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "matchResult")
public class MatchResult {
    @Id
    private final Integer matchNumber;
    private final String result;

    public MatchResult(String result, Integer matchNumber) {
        this.result = result;
        this.matchNumber = matchNumber;
    }

    public String getResult() {
        return result;
    }

    public int getMatchNumber() {
        return matchNumber;
    }


}
