package src;
import java.util.*;
public class Snake{
    private LinkedList<Position> body = new LinkedList<Position>();
    private Direction dir;
    private boolean growing;
    private SnakeState currentState = new Grounded(){};
    private int jumpLength = 2;

    Snake(Position pos){
        body.add(new Position(pos.getX()-1, pos.getY()));
        body.add(pos);
        dir = Direction.LEFT;
    }

    public void move(){
        Position nextPos = calcNextPos();
        body.add(nextPos);
        if(!growing){
            body.remove(0);
        } else{
            growing = false;
        }

        currentState.jumpFinished(this);
    }

    private Position calcNextPos(){
        Position currentHeadPos = body.get(body.size()-1);
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


    public boolean checkCol(){
        return currentState.checkCollision(this);
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
        dir = currentState.changeDir(this, newDir, dir);
    }

    public void jump(){
        changeState(new Airborne(jumpLength){});
    }

    public void endJump(){
        changeState(new Grounded());
    }

    private void changeState(SnakeState newState){
        currentState = newState;
    }

}
