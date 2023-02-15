package org.cricketgame.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "playerStats")
public class PlayerStats {
    @Id
    private String playerName;
    private int matchPlayed;
    private int runsScored;
    private int wicketTaken;
    public PlayerStats(String playerName, int matchPlayed, int runsScored, int wicketTaken) {
        this.playerName = playerName;
        this.matchPlayed = matchPlayed;
        this.runsScored = runsScored;
        this.wicketTaken = wicketTaken;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getMatchPlayed() {
        return matchPlayed;
    }

    public void setMatchPlayed(int matchPlayed) {
        this.matchPlayed = matchPlayed;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }

    public int getWicketTaken() {
        return wicketTaken;
    }

    public void setWicketTaken(int wicketTaken) {
        this.wicketTaken = wicketTaken;
    }
}
