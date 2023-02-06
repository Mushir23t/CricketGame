package org.example;

import java.util.ArrayList;

public class Team {
    private final ArrayList<Player> players = new ArrayList<>();
    private String TeamName;

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void AddPlayer(String s) {
        players.add(new Player(s));
    }

    public String GetTeamName() {
        return TeamName;
    }
}
