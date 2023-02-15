package org.cricketgame.model;

import java.util.Random;

public abstract class Ball {
    static int GetBall() {
        Random ball = new Random();
        return ball.nextInt(8);
    }

}
