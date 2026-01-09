package src;

public class Settings {
    private int sizeX;
    private int sizeY;
    private int sound;

    public Settings(int sizeX, int sizeY){
        this.sizeX=sizeX;
        this.sizeY=sizeY;
    }

    public int getSizeX(){
        return sizeX;
    }

    public int getSizeY(){
        return sizeY;
    }

    public void setsizeX(int sizeX){
        this.sizeX=sizeX;
        
    }
    public void setsizeY(int sizeY){
        
        this.sizeY=sizeY;
    }
}
