package src;

public abstract class SnakeState {
    public boolean checkCollision(Snake snake, Position position) {
        return false;
    }

    public void jumpFinished(Snake snake){

    }

    public boolean canEatFruit() {
        return true;
    }

    public boolean canChangeDirection(Direction newDir, Direction currentDir) {
        return ((newDir.getValue() + 2) % 4 != currentDir.getValue());
    }

    public abstract void jump(Snake snake);
}
