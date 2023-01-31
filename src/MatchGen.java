public class MatchGen {

    MatchGen() {
        Team Team1 = new Team();
        Team Team2 = new Team();
        String[] List1 = {"Virat", "Rohit", "Shikhar", "KL", "Rishabh", "Shreyas", "Hardik", "Ravindra", "Jasprit", "Mohammed", "Ishant"
        };
        String[] List2 = {"Steve", "David", "Marnus", "Glenn", "Marcus", "Matthew", "Cameron", "Tim", "Pat", "Mitchell", "Josh"
        };
        for (int i = 0; i < 11; i++) {
            Team1.AddPlayer(List1[i]);
        }
        for (int i = 0; i < 11; i++) {
            Team2.AddPlayer(List2[i]);
        }
        int target = Integer.MAX_VALUE;
        Innings I1 = new Innings(Team1, Team2);
        I1.BeginInn(target);
        Innings I2 = new Innings(Team2, Team1);
        I2.BeginInn(I1.Runs + 1);
        System.out.println("Team1 Score: " + I1.Runs + "/" + I1.Wickets);
        System.out.println("Team2 Score: " + I2.Runs + "/" + I2.Wickets);

        if (I1.Runs == I2.Runs) {
            System.out.println("Match is tied");
        } else if (I1.Runs > I2.Runs) {
            System.out.println("Team1 wins by " + (I1.Runs - I2.Runs) + " Runs");
        } else {
            System.out.println("Team2 Wins by " + (10 - I2.Wickets) + " Wickets");
        }
        System.out.println("INNINGS 1");
        System.out.println("BATTING");
        ScoreCard.PrintBattingScorecard(Team1);
        System.out.println();
        System.out.println("BOWLING");
        ScoreCard.PrintBowlingScorecard(Team2);

        System.out.println();
        System.out.println("INNINGS 2");
        System.out.println("BATTING");
        ScoreCard.PrintBattingScorecard(Team2);
        System.out.println();
        System.out.println("BOWLING");
        ScoreCard.PrintBowlingScorecard(Team1);

    }

}
