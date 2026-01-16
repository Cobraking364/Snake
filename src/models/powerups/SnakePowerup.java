package src.models.powerups;

import java.util.LinkedList;
import src.models.ISpaceOccupier;
import src.models.Position;
import src.models.Snake;

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
