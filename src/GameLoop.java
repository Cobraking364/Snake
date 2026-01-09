package src;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {

    private long lastTime = 0;

    @Override
    public void handle(long now) {
        if (lastTime == 0) {
            lastTime = now;
            return;
        }

        double deltaTime = (now - lastTime) / 1_000_000_000.0;
        lastTime = now;

        update(deltaTime);

    }

    public void update(double deltaTime) {
    }

}
