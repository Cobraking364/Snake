package src;

import java.util.*;

public class Snake {
    private LinkedList<Position> body = new LinkedList<Position>();
    private Direction dir;
    private boolean growing;
    private boolean turned;
    private SnakeState currentState;
    private int jumpLength = 2;

    Snake(Position pos) {
        body.add(new Position(pos.getX() + 1, pos.getY()));
        body.add(pos);
        growing = false;
        turned = false;
        dir = Direction.LEFT;
        currentState = new Grounded();
    }

    public void move(Position nextPosition) {
        body.add(nextPosition);
        if (!growing) {
            body.remove(0);
        } else {
            growing = false;
        }

        currentState.jumpFinished(this);
    }

    public Position getNextPosition() {
        Position currentHeadPos = getHead();
        Position nextPos;
        nextPos = switch (dir) {
            case Direction.UP -> new Position(currentHeadPos.getX(), currentHeadPos.getY() - 1);
            case Direction.LEFT -> new Position(currentHeadPos.getX() - 1, currentHeadPos.getY());
            case Direction.DOWN -> new Position(currentHeadPos.getX(), currentHeadPos.getY() + 1);
            case Direction.RIGHT -> new Position(currentHeadPos.getX() + 1, currentHeadPos.getY());
            default -> new Position(0, 0);
        };

        return nextPos;
    }

    public boolean checkCollision(Position position) {
        return currentState.checkCollision(this, position);
    }

    public Position getHead() {
        return body.get(body.size() - 1);
    }

    public LinkedList<Position> getBody() {
        return body;
    }

    public void grow() {
        growing = true;
    }

    public void updateDir(Direction newDir) {
        if (!canChanceDirection(newDir, dir)) {
            turned = false;
            return;
        }

        dir = newDir;
        turned = true;
    }

    public void jump() {
        currentState.jump(this);
    }

    public int getJumpLength() {
        return jumpLength;
    }

    public void changeState(SnakeState newState) {
        currentState = newState;
    }

    public boolean canEatFruit() {
        return currentState.canEatFruit();
    }

    public boolean canChanceDirection(Direction newDir, Direction currentDir) {
        return currentState.canChangeDirection(newDir, currentDir);
    }

    public boolean getTurned() {
        return turned;
    }

}
