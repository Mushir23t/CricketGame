package org.example;

import java.util.ArrayList;
import java.util.Objects;

public class ScoreCard {
    private final ArrayList<String> scoreCardStrings = new ArrayList<>();
    Innings I1, I2;

    ScoreCard(Match match) {
        this.I1 = match.getI1();
        this.I2 = match.getI2();
    }

    private void Innings1Batting() {
        PrintBattingScorecard(I1);
    }

    private void Innings1Bowling() {
        PrintBowlingScorecard(I1);
    }

    private void Innings2Batting() {
        PrintBattingScorecard(I2);
    }

    private void Innings2Bowling() {
        PrintBowlingScorecard(I2);
    }

    private ArrayList<String> PrintBattingScorecard(Innings I) {
        ArrayList<String> S = new ArrayList<>();
        Team Team = I.getBattingTeam();
        S.add(String.format("%-10s %7s %7s", "Name", "Balls", "Runs"));
        ArrayList<String> YetToBat = new ArrayList<>();
        for (Player p : Team.getPlayers()) {
            if (p.getBallsPlayed() != 0) {
                S.add(String.format("%-10s %7s %7s", p.getName(), p.getBallsPlayed(), p.getRunsScored()));
            } else
                YetToBat.add(p.getName());
        }
        S.add("Yet to Bat: ");
        for (String name : YetToBat)
            S.add(name + " ");
        return S;
    }

    private ArrayList<String> PrintBowlingScorecard(Innings I) {
        ArrayList<String> S = new ArrayList<>();
        Team Team = I.getBowlingTeam();
        S.add(String.format("%-10s %7s %10s %6s", "Name", "Overs", "Wickets", "Runs"));
        for (Player p : Team.getPlayers()) {
            double OversBowled = p.getOversBowled() + (p.getBallsBowled()) / 10.0;
            if (p.getBallsBowled() != 0 || p.getOversBowled() != 0) {
                S.add(String.format("%-10s %8s %8s %7s", p.getName(), OversBowled, p.getWickets(), p.getRunsConceded()));
            }
        }
        return S;
    }

    public void Print() {
        String str1 = I1.getBattingTeam().GetTeamName() + "  Score: " + I1.getRuns() + "/" + I1.getWickets();
        String str2 = I2.getBattingTeam().GetTeamName() + " Score: " + I2.getRuns() + "/" + I2.getWickets();
        scoreCardStrings.add(str1);
        scoreCardStrings.add(str2);
        if (Objects.equals(I1.getRuns(), I2.getRuns())) {
            String str3 = "Match is tied";
            scoreCardStrings.add(str3);
        } else if (I1.getRuns() > I2.getRuns()) {
            String str3 = I1.getBattingTeam().GetTeamName() + " wins by " + (I1.getRuns() - I2.getRuns()) + " Runs";
            scoreCardStrings.add(str3);
        } else {
            String str3 = I2.getBattingTeam().GetTeamName() + " wins by " + (10 - I2.getWickets()) + " Wickets";
            scoreCardStrings.add(str3);
        }
        String str4 = "INNINGS 1";
        scoreCardStrings.add(str4);
        scoreCardStrings.add("BATTING");
        ArrayList<String> S = PrintBattingScorecard(I1);
        scoreCardStrings.addAll(S);
        String str5 = "\nBOWLING";
        scoreCardStrings.add(str5);
        ArrayList<String> S1 = PrintBowlingScorecard(I1);
        scoreCardStrings.addAll(S1);
        String str6 = "INNINGS 2";
        scoreCardStrings.add(str6);
        scoreCardStrings.add("BATTING");
        ArrayList<String> S2 = PrintBattingScorecard(I2);
        scoreCardStrings.addAll(S2);
        String str7 = "\nBOWLING";
        scoreCardStrings.add(str7);
        ArrayList<String> S3 = PrintBowlingScorecard(I2);
        scoreCardStrings.addAll(S3);
    }

    public ArrayList<String> getStrings() {
        return scoreCardStrings;
    }
}


