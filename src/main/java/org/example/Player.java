package org.example;

public class Player {


    private static int cnt = 1;
    private final Integer number;
    private final String name;
    private Integer OversBowled = 0;
    private Integer BallsBowled = 0;
    private Integer BallsPlayed = 0;
    private Integer RunsConceded = 0;
    private int RunsScored = 0;
    private int wickets = 0;

    Player(String name) {
        this.name = name;
        this.number = cnt++;
    }

    public String getName() {
        return name;
    }

    public Integer getOversBowled() {
        return OversBowled;
    }

    public Integer getBallsBowled() {
        return BallsBowled;
    }

    public Integer getBallsPlayed() {
        return BallsPlayed;
    }

    public Integer getRunsConceded() {
        return RunsConceded;
    }

    public int getRunsScored() {
        return RunsScored;
    }

    public int getWickets() {
        return wickets;
    }

    void UpdateBowl(Integer Score) {
        if (Score == 7) {
            wickets++;
        } else
            RunsConceded += Score;
        BallsBowled++;
        if (BallsBowled % 6 == 0) {
            OversBowled++;
            BallsBowled = 0;
        }
    }

    void UpdateBat(Integer Score) {
        if (Score != 7)
            RunsScored += Score;
        BallsPlayed++;
    }
}
