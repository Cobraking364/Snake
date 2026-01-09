package src;

import java.lang.reflect.Array;
import java.util.*;

public class Board{
    private int sizeX;
    private int sizeY;
    private Snake snake;
    private int amountOfFruits = 10;
    private ArrayList<Fruit> fruits;
    private boolean isGameOver;
    private OccupiedSpace occupiedSpace;

    Board(int x, int y) {
        isGameOver = false;
        sizeX = x;
        sizeY = y;

        occupiedSpace = new OccupiedSpace();
        snake = new Snake(new Position(sizeX / 2, sizeY / 2), new Grounded());

        occupiedSpace.addOccupiedSpace(snake.getOccupiedSpace());

        fruits = new ArrayList<Fruit>();
        for (int i = 0; i < amountOfFruits; i++) {
            Fruit tempFruit = new Fruit();
            fruits.add(tempFruit);
        }

        for (Fruit fruit : fruits) {
            fruit.respawn(sizeX, sizeY, occupiedSpace.getOccupiedSpaces());
            occupiedSpace.addOccupiedSpace(fruit.getOccupiedSpace());
        }
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void update() {
        if (isGameOver) {
            return;
        }

        Position nextPosition = snake.getNextPosition();

        nextPosition.setX((nextPosition.getX() + sizeX) % sizeX);
        nextPosition.setY((nextPosition.getY() + sizeY) % sizeY);

        if (snake.checkCollision(nextPosition)) {
            gameOver();
            return;
        }

        int indexOfEaten = -1;

        for (int i  = 0; i <fruits.size(); i++) {
            boolean isEatingFruit = snake.canEatFruit() && fruits.get(i).getPosition().equals(nextPosition);
            if (isEatingFruit) {
                snake.grow();
                indexOfEaten = i;
                occupiedSpace.removeOccupiedSpace(nextPosition);
            }
        }
        occupiedSpace.removeOccupiedSpaces(snake.getOccupiedSpace());
        snake.move(nextPosition);
        occupiedSpace.addOccupiedSpace(snake.getOccupiedSpace());

        if (indexOfEaten != -1) {
            fruits.get(indexOfEaten).respawn(sizeX, sizeY, occupiedSpace.getOccupiedSpaces());
            occupiedSpace.addOccupiedSpace(fruits.get(indexOfEaten).getOccupiedSpace());
        }

    }

    private void gameOver() {
        isGameOver = true;
    }

    public boolean getIsGameOver() {
        return isGameOver;
    }

    public ArrayList<Fruit> getFruits() {
        return fruits;
    }

    public Snake getSnake() {
        return snake;
    }
}
