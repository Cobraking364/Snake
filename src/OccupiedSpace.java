package src;

import java.util.ArrayList;
import java.util.LinkedList;

public class OccupiedSpace {
    ArrayList<Position> occupiedSpaces = new ArrayList<>();;

    public ArrayList<Position> getOccupiedSpaces() {
        return occupiedSpaces;
    }

    public void addOccupiedSpace(LinkedList<Position> occupiedPosList){
        for (Position pos : occupiedPosList) {
            occupiedSpaces.add(pos);
        }
    }

    public void clearOccupiedSpace(){
        occupiedSpaces = new ArrayList<>();
    }
    
}
