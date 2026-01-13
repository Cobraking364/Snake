package src;

import java.util.*;

public class Snake implements ISpaceOccupier{
    private LinkedList<Position> body = new LinkedList<Position>();
    private Direction direction;
    private Direction previousDirection;
    private boolean growing;
    private boolean turned;
    private SnakeState currentState;
    private int jumpLength = 2;
    private boolean alive = true;

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
        currentState.update(this);
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

    public boolean checkCollision(Position position, ArrayList<Snake> otherSnakes) {
        return currentState.checkCollision(this, position, otherSnakes);
    }

    public Position getHead() {
        return body.get(body.size() - 1);
    }

    public LinkedList<Position> getBody() {
        return body;
    }

    @Override
    public LinkedList<Position> getOccupiedSpace(){
        return getBody();
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

    public void die(){
        alive = false;
    }

}
