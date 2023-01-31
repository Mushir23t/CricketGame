public class Player {
    static int cnt = 1;
    String name;
    Integer number, OversBowled = 0, BallsBowled = 0, BallsPlayed = 0, RunsConceded = 0;
    int RunsScored = 0, wickets = 0;

    Player(String name) {
        this.name = name;
        this.number = cnt++;
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
            RunsScored++;
        BallsPlayed++;
    }
}
