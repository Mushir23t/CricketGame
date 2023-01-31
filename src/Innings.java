public class Innings {
    Integer Runs = 0, Wickets = 0;
    private Integer Bowler, Batsman1 = 1, Batsman2 = 2, PrevBowler = -1;
    private Team BattingTeam, BowlingTeam;
    private boolean Strike = false, InningsEnded = false;

    Innings(Team BattingTeam, Team BowlingTeam) {
        this.BattingTeam = BattingTeam;
        this.BowlingTeam = BowlingTeam;
    }

    public void BeginInn(Integer Target) {
        for (int Over = 1; Over <= 20; Over++) {
            Bowler = Bowling.GetBowler(PrevBowler, BowlingTeam.OversBowledByPlayers);
            int ballsBowled = Over(Target);
            if (Over == 20 && ballsBowled == 6)
                InningsEnded = true;
            if (InningsEnded) {
                BattingTeam.RunsScored = Runs;
                BattingTeam.WicketsFelt = Wickets;
                break;
            }
            Strike = !Strike;
        }
    }

    private Integer NextBatsman() {
        return (Math.max(Batsman1, Batsman2) + 1);
    }

    private int Over(Integer Target) {
        Integer BallsBowled = 1;
        for (BallsBowled = 1; BallsBowled <= 6; BallsBowled++) {
            Integer Score = Ball.GetBall();
            UpdateStats(Score);
            if (Runs >= Target || Wickets == 10) {
                InningsEnded = true;
                break;
            }
        }
        return BallsBowled;
    }

    private void UpdateStats(Integer Score) {
        BowlingTeam.players.get(Bowler - 1).UpdateBowl(Score);
        if (Strike) {
            BattingTeam.players.get(Batsman1 - 1).UpdateBat(Score);
        } else {
            BattingTeam.players.get(Batsman2 - 1).UpdateBat(Score);
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
