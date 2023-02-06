package org.example;

import java.util.ArrayList;

public abstract class SpringUtility {
    private static ScoreCard Scard;
    private static Match match1;
    private static Team Team1, Team2;
    private static void Initialize() {
        Team1 = new Team();
        Team2 = new Team();
        String[] List1 = {"Virat", "Rohit", "Shikhar", "KL", "Rishabh", "Shreyas", "Hardik", "Ravindra", "Jasprit", "Mohammed", "Ishant"
        };
        String[] List2 = {"Steve", "David", "Marnus", "Glenn", "Marcus", "Matthew", "Cameron", "Tim", "Pat", "Mitchell", "Josh"
        };
        Team1.setTeamName("India");
        Team2.setTeamName("Australia");
        for (int i = 0; i < 11; i++) {
            Team1.AddPlayer(List1[i]);
        }
        for (int i = 0; i < 11; i++) {
            Team2.AddPlayer(List2[i]);
        }
    }
    static ArrayList<String> getStrings() {
        SpringUtility.Initialize();
        match1 = new Match(Team1, Team2);
        match1.RunMatch();
        Scard = new ScoreCard(match1);
        Scard.Print();
        return Scard.getStrings();
    }

}
