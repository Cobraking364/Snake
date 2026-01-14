package src;

import java.util.*;

public class Fruit implements ISpaceOccupier{
    private Position position;


    public void respawn(int sizeX, int sizeY, List<Position> snakeBody) {
        List<Position> possiblePositions = new ArrayList<>();
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                possiblePositions.add(new Position(i, j));
            }
        }
        int listIndex = 0;
        int maxIndex = sizeX * sizeY - 1;
        Collections.shuffle(possiblePositions);
        while (isInList(possiblePositions.get(listIndex), snakeBody)) {
            if (listIndex >= maxIndex) {
                position = new Position(-1, -1);
                return;
            }
            listIndex++;
        }
        position = possiblePositions.get(listIndex);

    }

    private boolean isInList(Position position, List<Position> list) {
        for (Position position2 : list) {
            if (position.equals(position2)) {
                return true;
            }
        }
        return false;
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
