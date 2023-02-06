package org.example;

import java.util.HashMap;
import java.util.Random;

public class BowlerUtility {
    public static Integer GetBowler(Integer prevBowler, HashMap<Integer, Integer> OversBowledByPlayers) {
        Random num = new Random();
        Integer BowlerNumber = num.nextInt(11) + 1;
        if (OversBowledByPlayers.containsKey(BowlerNumber)) {
            while (OversBowledByPlayers.get(BowlerNumber) > 4 || BowlerNumber == prevBowler) {
                BowlerNumber = (BowlerNumber + num.nextInt(11)) % 11 + 1;
                if (!OversBowledByPlayers.containsKey(BowlerNumber)) {
                    break;
                }
            }
            if (OversBowledByPlayers.containsKey(BowlerNumber))
                OversBowledByPlayers.put(BowlerNumber, OversBowledByPlayers.get(BowlerNumber) + 1);
            else
                OversBowledByPlayers.put(BowlerNumber, 1);
        } else {
            OversBowledByPlayers.put(BowlerNumber, 1);
        }

        prevBowler = BowlerNumber;

        return prevBowler;
    }

}