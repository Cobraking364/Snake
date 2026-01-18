package src.models;

import javafx.scene.input.KeyCode;

public class Settings {
    private int sizeX;
    private int sizeY;
    private int soundVolume;
    private int snakeSpeed;
    private int fruitCount;
    private int playerCount;
    private KeyControls[] keyControls;
    private final int DEFAULT_SIZE_X = 15;
    private final int DEFAULT_SIZE_Y = 15;
    private final int DEFAULT_SOUND_VOLUME = 50;
    private final int DEFAULT_SNAKE_SPEED = 8;
    private final int DEFAULT_FRUIT_COUNT = 1;
    private final int DEFAULT_PLAYER_COUNT = 1;
    private final KeyControls[] DEFAULT_KEY_CONTROLS = {
            new KeyControls(KeyCode.UP, KeyCode.LEFT, KeyCode.DOWN, KeyCode.RIGHT, KeyCode.MINUS),
            new KeyControls(KeyCode.W, KeyCode.A, KeyCode.S, KeyCode.D, KeyCode.Q),
            new KeyControls(KeyCode.I, KeyCode.J, KeyCode.K, KeyCode.L, KeyCode.U),
            new KeyControls(KeyCode.G, KeyCode.V, KeyCode.B, KeyCode.N, KeyCode.F),
    };

    public Settings(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.soundVolume = DEFAULT_SOUND_VOLUME;
        this.snakeSpeed = DEFAULT_SNAKE_SPEED;
        this.fruitCount = DEFAULT_FRUIT_COUNT;
        this.playerCount = DEFAULT_PLAYER_COUNT;
        this.keyControls = DEFAULT_KEY_CONTROLS;
    }
    
    public Settings() {
        this.sizeX = DEFAULT_SIZE_X;
        this.sizeY = DEFAULT_SIZE_Y;
        this.soundVolume = DEFAULT_SOUND_VOLUME;
        this.snakeSpeed = DEFAULT_SNAKE_SPEED;
        this.fruitCount = DEFAULT_FRUIT_COUNT;
        this.playerCount = DEFAULT_PLAYER_COUNT;
        this.keyControls = DEFAULT_KEY_CONTROLS;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }


    public void setSnakeSpeed(int snakeSpeed) {

        this.snakeSpeed = snakeSpeed;
    }

    public int getSnakeSpeed() {
        return snakeSpeed;
    }

    public int getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(int value) {
        soundVolume = value;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int value) {
        playerCount = value;
    }

    public int getFruitCount() {
        return fruitCount;
    }

    public void setFruitCount(int value) {
        fruitCount = value;
    }

    public KeyControls[] getKeyControls() {
        return keyControls;
    }

}