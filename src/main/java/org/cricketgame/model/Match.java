package org.cricketgame.model;

public class Match {
    private static Integer counter = 1;
    private final int MatchNumber;
    private final Team Team1;
    private final Team Team2;
    private Innings I1, I2;
    private Team WinnerTeam;


    public Match(Team Team1, Team Team2) {
        this.MatchNumber = counter++;
        this.Team1 = Team1;
        this.Team2 = Team2;
    }

    public Innings getI1() {
        return I1;
    }

    public Innings getI2() {
        return I2;
    }

    public Team getWinnerTeam() {
        return WinnerTeam;
    }

    public int getMatchNumber() {
        return MatchNumber;
    }

    public void RunMatch() {

        I1 = new Innings(Team1, Team2);
        I1.BeginInn(Integer.MAX_VALUE);
        I2 = new Innings(Team2, Team1);
        I2.BeginInn(I1.getRuns() + 1);
    }

}
