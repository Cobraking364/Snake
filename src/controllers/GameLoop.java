package src.controllers;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {

    private long lastTime;
    private int fps;
    private double accumalatedTime;
    private double frameDuration;



    GameLoop(int fps) {
        this.fps = fps;
        frameDuration = 1.0 / fps;
        lastTime = 0;
        accumalatedTime = 0;
    }

    @Override
    public void handle(long now) {
        if (lastTime == 0) {
            lastTime = now;
            return;
        }
        double deltaTime = (now - lastTime) / 1_000_000_000.0;
        accumalatedTime += deltaTime;
        if (accumalatedTime > frameDuration) {
            update(frameDuration);
            accumalatedTime -= frameDuration;
        }
        lastTime = now;
    }

    public void update(double deltaTime) {
    }

}
