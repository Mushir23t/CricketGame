package org.example;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document
public class MatchResult {

    private String result;

    public MatchResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }


}
