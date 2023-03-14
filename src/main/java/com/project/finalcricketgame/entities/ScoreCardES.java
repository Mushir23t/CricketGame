package com.project.finalcricketgame.entities;


import com.project.finalcricketgame.dto.BattingStatsDTO;
import com.project.finalcricketgame.dto.BowlingStatsDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "scorecard")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScoreCardES {
    @Id
    private String id;
    private int firstInningsTotal;
    private int FirstInningsWicket;
    private int secondInningsTotal;
    private int secondInningsWicket;
    private int MatchId;
    private List<BattingStatsDTO> team1battingStats = new ArrayList<>();
    private List<BowlingStatsDTO> team2bowlingStats = new ArrayList<>();
    private List<BattingStatsDTO> team2battingStats = new ArrayList<>();
    private List<BowlingStatsDTO> team1bowlingStats = new ArrayList<>();


}
