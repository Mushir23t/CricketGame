import java.util.Random;

public abstract class Ball {
    static int GetBall(){
        Random ball = new Random();
        return ball.nextInt(0,8);
    }
}
