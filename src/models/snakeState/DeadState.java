package src.models.snakeState;

import src.models.Direction;
import src.models.Position;
import src.models.Snake;

public class DeadState extends SnakeState {
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
