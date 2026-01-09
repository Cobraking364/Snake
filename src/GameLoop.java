package src;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {

    private long lastTime = 0;
    private int fps;



    GameLoop(int fps) {
        this.fps = fps;
    }

    @Override
    public void handle(long now) {
        if (lastTime == 0) {
            lastTime = now;
            return;
        }

        double deltaTime = (now - lastTime) / 1_000_000_000.0;
        if (deltaTime > 1.0 / fps) {
            update(deltaTime);
            lastTime = now;
        }
    }

    public void update(double deltaTime) {
    }

}
