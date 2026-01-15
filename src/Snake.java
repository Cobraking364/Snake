package src;

import java.util.*;

public class Snake implements ISpaceOccupier{
    private LinkedList<Position> body = new LinkedList<Position>();
    private Direction direction;
    private Direction previousDirection;
    private boolean growing;
    private boolean turned;
    private boolean hasJumped;
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

    public void move(Position nextPosition, double  deltaTime) {
        body.add(nextPosition);
        previousDirection = direction;
        if (!growing) {
            body.remove(0);
        } else {
            growing = false;
        }
        setHasJumped(false);
        currentState.update(this, deltaTime);
    }

    public Position getNextPosition() {
        return currentState.getNextPosition(getHead(), direction);

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

    // Uses turning logic
    public void updateDirection(Direction newDir) {
        if (!canChanceDirection(newDir, previousDirection)) {
            turned = false;
            return;
        }
        setDirection(newDir);
        turned = true;
    }

    // Ignores turning logic
    public void setDirection(Direction newDirection) {
        direction = newDirection;
    }

    public void setPreviousDirection(Direction newDirection) {
        previousDirection = newDirection;
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
    public boolean getHasJumped() {
        return hasJumped;
    }
    public void setHasJumped(boolean a) {
        hasJumped = a;
    }

    public Direction getDirection(){
        return direction;
    }

    public void die(){
        System.out.println("Snake died");
        alive = false;
        changeState(new DeadState());
    }

    public boolean getLivingStatus(){
        return alive;
    }

}
