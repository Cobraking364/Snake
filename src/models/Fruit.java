package src;

import java.util.*;

public class Fruit implements ISpaceOccupier{
    private Position position;


    public void respawn(Position position) {
        this.position = position;
    }



    public Position getPosition(){
        return position;
    }

    @Override
    public LinkedList<Position> getOccupiedSpace(){
        LinkedList<Position> temp = new LinkedList<>();
        temp.add(getPosition());
        return temp;
    }
}
