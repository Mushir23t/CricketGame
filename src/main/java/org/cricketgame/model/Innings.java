package org.cricketgame.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Innings {
    private final Team BattingTeam;
    private final Team BowlingTeam;
    private final ArrayList<Over> Overs = new ArrayList<>();
    private final HashMap<Integer, Integer> OversBowledByPlayers = new HashMap<>();
    private Integer Runs = 0, Wickets = 0, Bowler = -1, Batsman1 = 1, Batsman2 = 2;
    private boolean Strike = false, InningsEnded = false;

    Innings(Team BattingTeam, Team BowlingTeam) {
        this.BattingTeam = BattingTeam;
        this.BowlingTeam = BowlingTeam;
    }

    public Team getBattingTeam() {
        return BattingTeam;
    }

    public Team getBowlingTeam() {
        return BowlingTeam;
    }

    public Integer getRuns() {
        return Runs;
    }

    public Integer getWickets() {
        return Wickets;
    }

    public void BeginInn(Integer Target) {
        for (int Over = 1; Over <= 20; Over++) {
            Bowler = BowlerUtility.GetBowler(Bowler, OversBowledByPlayers);
            Over curOver = new Over();
            int ballsBowled = BeginOver(Target, curOver);
            if (Over == 20 && ballsBowled == 6) InningsEnded = true;
            if (InningsEnded) {
                Overs.add(curOver);
                break;
            }
            Strike = !Strike;
            Overs.add(curOver);
        }
    }

    private Integer NextBatsman() {
        return (Math.max(Batsman1, Batsman2) + 1);
    }

    public int BeginOver(Integer Target, Over curOver) {
        Integer BallsBowled = 1;
        for (BallsBowled = 1; BallsBowled <= 6; BallsBowled++) {
            Integer Score = Ball.GetBall();
            curOver.setBalls(Score);
            UpdateStats(Score);
            if (Runs >= Target || Wickets == 10) {
                InningsEnded = true;
                break;
            }
        }
        return BallsBowled;
    }

    private void UpdateStats(Integer Score) {
        BowlingTeam.getPlayers().get(Bowler - 1).UpdateBowlingStats(Score);
        if (Strike) {
            BattingTeam.getPlayers().get(Batsman1 - 1).UpdateBattingStats(Score);
        } else {
            BattingTeam.getPlayers().get(Batsman2 - 1).UpdateBattingStats(Score);
        }
        if (Score == 7) {
            Wickets++;
            if (Strike) {
                Batsman1 = NextBatsman();
            } else {
                Batsman2 = NextBatsman();
            }
        } else {
            if (Score % 2 == 1) Strike = !Strike;
            Runs += Score;
        }

    }


}
