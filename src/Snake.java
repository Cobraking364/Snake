package src;
import java.util.*;
public class Snake {
    private LinkedList<Position> body = new LinkedList<Position>();
    private Direction dir;
    private boolean growing;

    Snake(){
        body.add(new Position(0, 0));
        body.add(new Position(1, 0));
        dir = Direction.LEFT;
    }

    public boolean move(){
        Position nextPos = calcNextPos();
        body.add(nextPos);
        if(!growing){
            body.remove(0);
        } else{
            growing = false;
        }
        return false;
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
        if((newDir.getValue()+2)%4 != dir.getValue()){
            dir = newDir;
        }
    }

}
