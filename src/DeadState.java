package src;

import java.util.ArrayList;

public class DeadState extends SnakeState {

    @Override
    public boolean checkCollision(Snake snake, Position position, ArrayList<Snake> otherSnakes) {
        return false;
    }

    @Override
    public boolean canChangeDirection(Direction newDir, Direction currentDir) {
        return false;
    }
    @Override
    public boolean canEatFruit() {
        return false;
    }

    @Override
    public void jump(Snake snake) {
    }
    
    @Override
    public Position getNextPosition(Position currentHeadPos, Direction direction) {
        return currentHeadPos;
    }

    @Override
    public void update(Snake snake, double deltaTime){
        
    }

}
