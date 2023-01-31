import java.util.ArrayList;

public abstract class ScoreCard {
    public static void PrintBattingScorecard(Team Team) {
        System.out.printf("%-10s %7s %7s \n", "Name", "Balls", "Runs");
        //System.out.println("Name" + "      B" + " R ");
        ArrayList<String> YetToBat = new ArrayList<>();
        for (Player p : Team.players) {
            if (p.BallsPlayed != 0) {

                System.out.printf("%-10s %7s %7s \n", p.name, p.BallsPlayed, p.RunsScored);
            } else
                YetToBat.add(p.name);
        }
        System.out.println("Yet to Bat: ");
        for (String name : YetToBat)
            System.out.print(name + " ");
    }

    public static void PrintBowlingScorecard(Team Team) {
        System.out.printf("%-10s %7s %10s %6s \n", "Name", "Overs", "Wickets", "Runs");
        for (Player p : Team.players) {
            double OversBowled = p.OversBowled + (p.BallsBowled) / 10.0;
            if (p.BallsBowled != 0 || p.OversBowled != 0) {
                System.out.printf("%-10s %7s %7s %7s \n", p.name, OversBowled, p.wickets, p.RunsConceded);
            }
        }
    }
}
