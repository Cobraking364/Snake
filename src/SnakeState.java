package src;

public class SnakeState {
    public boolean checkCollision(Snake snake, Position position) {
        return false;
    }

    public boolean canEatFruit() {
        return true;
    };

    public boolean canChangeDirection(Direction newDir, Direction currentDir) {
        return ((newDir.getValue() + 2) % 4 != currentDir.getValue());
    }
}
