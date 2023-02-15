package org.cricketgame.service;

import org.cricketgame.model.Match;
import org.cricketgame.model.ScoreCard;
import org.cricketgame.model.Team;

import java.util.ArrayList;

import static org.cricketgame.service.HardCodingTeam.TeamInitializer;

public class UtilityForEachMatch {
    private Match match1;

    private Team Team1, Team2;

    public String getMatchResultString() {

        Pair<Team, Team> Teams = TeamInitializer();
        Team1 = Teams.getFirst();
        Team2 = Teams.getSecond();
        match1 = new Match(Team1, Team2);
        match1.RunMatch();
        ScoreCard scard = new ScoreCard(match1);
        scard.SaveScorecard();
        ArrayList<String> s = scard.getStrings();
        String finalString = String.join("<br>", s);
        return finalString;
    }

    Pair<Team, Team> getTeams() {
        return new Pair<>(Team1, Team2);
    }

    public static class Pair<T, U> {
        private final T first;
        private final U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public U getSecond() {
            return second;
        }

    }
}
