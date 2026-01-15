package src;

import java.util.ArrayList;

public abstract class SnakeState {
    
    public boolean checkCollision(Snake snake, Position position, ArrayList<Snake> otherSnakes) {
        return false;
    }

    public void update(Snake snake, double deltaTime){

    }

    public boolean canEatFruit() {
        return true;
    }

    public boolean canChangeDirection(Direction newDir, Direction currentDir) {
        return ((newDir.getValue() + 2) % 4 != currentDir.getValue());
    }

    public abstract void jump(Snake snake);

    public Position getNextPosition(Position currentHeadPos, Direction direction) {
        Position nextPos;
        nextPos = switch (direction) {
            case Direction.UP -> new Position(currentHeadPos.getX(), currentHeadPos.getY() - 1);
            case Direction.LEFT -> new Position(currentHeadPos.getX() - 1, currentHeadPos.getY());
            case Direction.DOWN -> new Position(currentHeadPos.getX(), currentHeadPos.getY() + 1);
            case Direction.RIGHT -> new Position(currentHeadPos.getX() + 1, currentHeadPos.getY());
            default -> new Position(0, 0);
        };

        return nextPos;
    }
}
