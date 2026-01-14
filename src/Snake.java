package src;

import java.util.*;

public class Snake {
    private LinkedList<Position> body = new LinkedList<Position>();
    private Direction direction;
    private Direction previousDirection;
    private boolean growing;
    private boolean turned;
    private boolean hasJumped;
    private SnakeState currentState;
    private int jumpLength = 2;

    Snake(Position pos, SnakeState initialState){
        body.add(new Position(pos.getX()+1, pos.getY()));
        body.add(pos);
        growing = false;
        turned = false;
        direction = Direction.LEFT;
        previousDirection = Direction.LEFT;
        currentState = initialState;
    }

    public void move(Position nextPosition) {
        body.add(nextPosition);
        previousDirection = direction;
        if (!growing) {
            body.remove(0);
        } else {
            growing = false;
        }

        currentState.jumpFinished(this);
        hasJumped = false;
    }

    public Position getNextPosition() {
        Position currentHeadPos = getHead();
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


    public void updateDirection(Direction newDir) {
        if (!canChanceDirection(newDir, previousDirection)) {
            turned = false;
            return;
        }

        direction = newDir;
        turned = true;
    }

    public void jump() {
        currentState.jump(this);
        hasJumped = true;
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
    public boolean getHasJumped() {
        return hasJumped;
    }

}
