package src;
import java.util.*;
public class Snake{
    private LinkedList<Position> body = new LinkedList<Position>();
    private Direction dir;
    private boolean growing;
    private boolean turned;
    private SnakeState currentState = new Grounded();

    Snake(Position pos){
        body.add(new Position(pos.getX()+1, pos.getY()));
        body.add(pos);
        dir = Direction.LEFT;
    }

    public void move(Position nextPosition){
        body.add(nextPosition);
        if(!growing){
            body.remove(0);
        } else{
            growing = false;
        }
    }

    public Position getNextPosition(){
        Position currentHeadPos = getHead();
        Position nextPos;
        switch(dir){
            case Direction.UP:
                nextPos = new Position(currentHeadPos.getX(), currentHeadPos.getY()-1);
                break;
            case Direction.LEFT:
                nextPos = new Position(currentHeadPos.getX()-1, currentHeadPos.getY());
                break;
            case Direction.DOWN:
                nextPos = new Position(currentHeadPos.getX(), currentHeadPos.getY()+1);
                break;
            case Direction.RIGHT:
                nextPos = new Position(currentHeadPos.getX()+1, currentHeadPos.getY());
                break;
            default:
                nextPos = new Position(0, 0);
        }

        return nextPos;
    }


    public boolean checkCollision(Position position){
        return currentState.checkCollision(this, position);
    }

    public Position getHead(){
        return body.get(body.size()-1);
    }

    public LinkedList<Position> getBody(){
        return body;
    }

    public void grow(){
        growing = true; 
    }

    public void updateDir(Direction newDir){
        if (!canChanceDirection(newDir, dir)) {
            turned = false;
            return;
        }

        dir = newDir;
        turned = true;
    }

    private void changeState(SnakeState newState){
        currentState = newState;
    }

    public boolean canEatFruit(){
        return currentState.canEatFruit();
    }

    public boolean canChanceDirection(Direction newDir, Direction currentDir) {
        return currentState.canChangeDirection(newDir, currentDir);
    }

    public boolean getTurned() {
        return turned;
    }
}
