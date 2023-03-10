package com.project.finalcricketgame.entities;

import com.project.finalcricketgame.dto.BattingStatsDTO;
import com.project.finalcricketgame.dto.BowlingStatsDTO;
import lombok.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "scorecard")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ScoreCardMongo {
    @Id
    private String id;
    private int firstInningsTotal;
    private int FirstInningsWicket;
    private int secondInningsTotal;
    private int secondInningsWicket;
    private int matchId;
    private ArrayList<BattingStatsDTO> team1battingStats = new ArrayList<>();
    private ArrayList<BowlingStatsDTO> team2bowlingStats = new ArrayList<>();
    private ArrayList<BattingStatsDTO> team2battingStats = new ArrayList<>();
    private ArrayList<BowlingStatsDTO> team1bowlingStats = new ArrayList<>();


    public ScoreCardMongo(ScoreCard scoreCard) {
        this.id = scoreCard.getId();
        this.firstInningsTotal = scoreCard.getFirstInningsTotal();
        this.FirstInningsWicket = scoreCard.getFirstInningsWicket();
        this.secondInningsTotal = scoreCard.getSecondInningsTotal();
        this.secondInningsWicket = scoreCard.getSecondInningsWicket();
        this.matchId = scoreCard.getMatchId();
        this.team1battingStats = scoreCard.getTeam1battingStats();
        this.team2battingStats = scoreCard.getTeam2battingStats();
        this.team1bowlingStats = scoreCard.getTeam1bowlingStats();
        this.team2bowlingStats = scoreCard.getTeam2bowlingStats();
    }
}
