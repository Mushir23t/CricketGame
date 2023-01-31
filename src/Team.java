import java.util.ArrayList;
import java.util.HashMap;

public class Team {
    ArrayList<Player> players = new ArrayList<>();
    HashMap<Integer, Integer> OversBowledByPlayers = new HashMap<>();
    Integer RunsScored = 0, WicketsFelt = 0;
    void AddPlayer(String s)
    {
        players.add(new Player(s));
    }
}
