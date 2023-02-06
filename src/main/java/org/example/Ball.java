package org.example;

import java.util.Random;

public abstract class Ball {
    static int GetBall() {
        Random ball = new Random();
        return ball.nextInt(8);
    }
}
