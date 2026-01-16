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

    public void removeOccupiedSpace(Position pos){
        int storedIndex = -1;
        for(int i = 0; i< occupiedSpaces.size(); i++){
            if(occupiedSpaces.get(i).equals(pos)){
                storedIndex = i;
            }
        }
        if (storedIndex != -1) {
            occupiedSpaces.remove(storedIndex);
        }
    }

    public void removeOccupiedSpaces(LinkedList<Position> positions){
        for(Position pos : positions){
            removeOccupiedSpace(pos);
        }
    }

    public void clearOccupiedSpace(){
        occupiedSpaces = new ArrayList<>();
    }
    
}
