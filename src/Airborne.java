package src;

public interface Airborne extends SnakeState{
    @Override
    default boolean checkCollision(Snake snake) {
        return false;
    }

    @Override
    default Direction changeDir(Snake snake, Direction newDir, Direction currentDir){
        return currentDir;
    }
    
}
