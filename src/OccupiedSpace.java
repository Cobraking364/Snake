package src;

import java.util.ArrayList;
import java.util.LinkedList;

import javafx.geometry.Pos;

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
        int storedIndex = 0;
        for(int i = 0; i< occupiedSpaces.size()-1; i++){
            if(occupiedSpaces.get(i).equals(pos)){
                storedIndex = i;
            }
        }
        occupiedSpaces.remove(storedIndex);
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
