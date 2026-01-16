package src;

import java.util.LinkedList;

public abstract class SnakePowerup implements ISpaceOccupier{
    private Position position;

    public SnakePowerup(Position position) {
        this.position = position;
    }
    
    public abstract void use(Snake snake);

    public Position getPosition() {
        return position;
    }
    
    @Override
    public LinkedList<Position> getOccupiedSpace(){
        LinkedList<Position> positionList = new LinkedList<>();
        positionList.add(getPosition());
        return positionList;
    }
}
