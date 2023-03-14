package com.project.finalcricketgame.entities;

import com.project.finalcricketgame.dto.BattingStatsDTO;
import com.project.finalcricketgame.dto.BowlingStatsDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "scorecard")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScoreCard {
    @Id
    private String id;
    private int firstInningsTotal;
    private int FirstInningsWicket;
    private int secondInningsTotal;
    private int secondInningsWicket;
    private int matchId;
    private List<BattingStatsDTO> team1battingStats = new ArrayList<>();
    private List<BowlingStatsDTO> team2bowlingStats = new ArrayList<>();
    private List<BattingStatsDTO> team2battingStats = new ArrayList<>();
    private List<BowlingStatsDTO> team1bowlingStats = new ArrayList<>();


    public ScoreCard(ScoreCardES scoreCardES) {
        this.id = scoreCardES.getId();
        this.firstInningsTotal = scoreCardES.getFirstInningsTotal();
        this.FirstInningsWicket = scoreCardES.getFirstInningsWicket();
        this.secondInningsTotal = scoreCardES.getSecondInningsTotal();
        this.secondInningsWicket = scoreCardES.getSecondInningsWicket();
        this.matchId = scoreCardES.getMatchId();
        this.team1battingStats = scoreCardES.getTeam1battingStats();
        this.team2battingStats = scoreCardES.getTeam2battingStats();
        this.team1bowlingStats = scoreCardES.getTeam1bowlingStats();
        this.team2bowlingStats = scoreCardES.getTeam2bowlingStats();
    }
}
