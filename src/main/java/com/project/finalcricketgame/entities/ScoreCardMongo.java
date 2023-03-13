package com.project.finalcricketgame.entities;

import com.project.finalcricketgame.dto.BattingStatsDTO;
import com.project.finalcricketgame.dto.BowlingStatsDTO;
import lombok.*;
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


    public ScoreCardMongo(ScoreCardES scoreCardES) {
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
