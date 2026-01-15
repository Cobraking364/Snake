package src;

public class Settings {
    private int sizeX;
    private int sizeY;
    private int soundVolume;
    private int snakeSpeed;
    private int fruitCount;
    private int playerCount;
    private final int DEFAULT_SIZE_X = 15;
    private final int DEFAULT_SIZE_Y = 15;
    private final int DEFAULT_SOUND_VOLUME = 50;
    private final int DEFAULT_SNAKE_SPEED = 8;
    private final int DEFAULT_FRUIT_COUNT = 1;
    private final int DEFAULT_PLAYER_COUNT = 1;

    Settings(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.soundVolume = DEFAULT_SOUND_VOLUME;
        this.snakeSpeed = DEFAULT_SNAKE_SPEED;
        this.fruitCount = DEFAULT_FRUIT_COUNT;
        this.playerCount = DEFAULT_PLAYER_COUNT;
    }

    Settings() {
        this.sizeX = DEFAULT_SIZE_X;
        this.sizeY = DEFAULT_SIZE_Y;
        this.soundVolume = DEFAULT_SOUND_VOLUME;
        this.snakeSpeed = DEFAULT_SNAKE_SPEED;
        this.fruitCount = DEFAULT_FRUIT_COUNT;
        this.playerCount = DEFAULT_PLAYER_COUNT;
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
}
