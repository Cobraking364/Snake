package src;

public class SnakeState {
    public boolean checkCollision(Snake snake){ return false;}
    public Direction changeDir(Snake snake, Direction newDir, Direction currentDir){return Direction.LEFT;}
    public void jumpFinished(Snake snake){};
}
