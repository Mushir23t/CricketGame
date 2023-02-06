package org.example;

import java.util.ArrayList;

public class Over {
    private final ArrayList<String> Balls = new ArrayList<>();

    public ArrayList<String> getBalls() {
        return Balls;
    }

    public void setBalls(Integer ball) {
        if (ball == 7)
            Balls.add("W");
        else
            Balls.add(ball.toString());
    }

}
