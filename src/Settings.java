package src;

public class Settings {
    private int sizeX;
    private int sizeY;
    private int soundVolume;
    private int snakeSpeed = 8;
    private int fruitCount;
    private int playerCount;

    public Settings(int sizeX, int sizeY){
        this.sizeX=sizeX;
        this.sizeY=sizeY;
    }

    public int getSizeX(){
        return sizeX;
    }

    public void setSizeX(int sizeX){
        this.sizeX=sizeX;
    }
    
    public int getSizeY(){
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }
    
    public void setSnakeSpeed(int sizeY){
        
        this.sizeY=sizeY;
    }

    public void getSnakeSpeed(int value) {
        snakeSpeed = value;
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
