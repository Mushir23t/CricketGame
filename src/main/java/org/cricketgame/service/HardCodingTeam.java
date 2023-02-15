package org.cricketgame.service;

import org.cricketgame.model.Team;


public class HardCodingTeam {

    private static Team Team1, Team2;

    public static UtilityForEachMatch.Pair<Team, Team> TeamInitializer() {
        Team1 = new Team();
        Team2 = new Team();
        String[] List1 = {"Virat", "Rohit", "Shikhar", "KL", "Rishabh", "Shreyas", "Hardik", "Ravindra", "Jasprit", "Mohammed", "Ishant"};
        String[] List2 = {"Steve", "David", "Marnus", "Glenn", "Marcus", "Matthew", "Cameron", "Tim", "Pat", "Mitchell", "Josh"};
        Team1.setTeamName("India");
        Team2.setTeamName("Australia");
        for (int i = 0; i < 11; i++) {
            Team1.AddPlayer(List1[i]);
        }
        for (int i = 0; i < 11; i++) {
            Team2.AddPlayer(List2[i]);
        }
        UtilityForEachMatch.Pair<Team, Team> teamPair = new UtilityForEachMatch.Pair<>(Team1, Team2);
        return teamPair;
    }


}
