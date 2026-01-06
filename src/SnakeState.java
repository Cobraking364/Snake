package src;

public interface SnakeState {
    default public boolean checkCollision(Snake snake){ return false;}
    default public Direction changeDir(Snake snake, Direction newDir, Direction currentDir){return Direction.LEFT;}
}
