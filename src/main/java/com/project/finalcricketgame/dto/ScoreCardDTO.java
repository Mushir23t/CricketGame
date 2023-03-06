package com.project.finalcricketgame.dto;


import lombok.AllArgsConstructor;
import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "scorecard")
@Getter
@Setter
public class ScoreCardDTO {

    @Id
    private String id;

    private int firstInningsTotal;
    private int FirstInningsWicket;
    private int secondInningsTotal;
    private int secondInningsWicket;
    private int MatchId;

    private ArrayList<BattingStatsDTO> team1battingStats = new ArrayList<>();
    private ArrayList<BowlingStatsDTO> team2bowlingStats = new ArrayList<>();
    private ArrayList<BattingStatsDTO> team2battingStats = new ArrayList<>();
    private ArrayList<BowlingStatsDTO> team1bowlingStats = new ArrayList<>();


}
