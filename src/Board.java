package src;

import java.util.*;

public class Board{
    private int sizeX;
    private int sizeY;
    private Snake snake;
    private int amountOfFruits;
    private int amountOfSnakes;
    private ArrayList<Fruit> fruits;
    private ArrayList<Snake> snakes;
    private boolean isSinglePlayer = true;
    private boolean isGameOver;
    private OccupiedSpace occupiedSpace;

    Board(int x, int y, int amountOfFruits, int amountOfSnakes) {
        isGameOver = false;
        sizeX = x;
        sizeY = y;
        this.amountOfFruits = amountOfFruits;
        this.amountOfSnakes = amountOfSnakes;

        if(this.amountOfSnakes > 1){
            isSinglePlayer = false;
        }

        occupiedSpace = new OccupiedSpace();
        snakes = new ArrayList<Snake>();
        if (amountOfSnakes == 1) {
            snake = new Snake(new Position(sizeX / 2, sizeY / 2), new Grounded());
            occupiedSpace.addOccupiedSpace(snake.getOccupiedSpace());
            snakes.add(snake);
        } else{
            for (int i = 0; i<amountOfSnakes; i++) {
                Snake tempSnake = new Snake(new Position(5 * i, y), new Grounded());
                occupiedSpace.addOccupiedSpace(tempSnake.getOccupiedSpace());
                snakes.add(tempSnake);
            }
        }

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

        boolean allDead = true;
        for (Snake snake : snakes) {
            if (snake.getLivingStatus()) {
                allDead = false;
            }
        }
        if (allDead) {
            gameOver();
        }

        ArrayList<Position> nextPositions = new ArrayList<Position>();

        for (Snake snake : snakes) {
            Position nextPosition = snake.getNextPosition();
            nextPositions.add(nextPosition);

            nextPosition.setX((nextPosition.getX() + sizeX) % sizeX);
            nextPosition.setY((nextPosition.getY() + sizeY) % sizeY);

            if (snake.checkCollision(nextPosition, snakes)) {
                if (isSinglePlayer || !isSinglePlayer) {
                    gameOver();
                    return;
                } else{
                    snake.die();
                }
            }

            int indexOfEaten = -1;

            for (int i = 0; i < fruits.size(); i++) {
                boolean isEatingFruit = snake.canEatFruit() && fruits.get(i).getPosition().equals(nextPosition);
                if (isEatingFruit) {
                    snake.grow();
                    indexOfEaten = i;
                }
            }
            occupiedSpace.removeOccupiedSpaces(snake.getOccupiedSpace());
            snake.move(nextPosition);
            occupiedSpace.addOccupiedSpace(snake.getOccupiedSpace());

            if (indexOfEaten != -1) {
                occupiedSpace.removeOccupiedSpace(fruits.get(indexOfEaten).getPosition());
                fruits.get(indexOfEaten).respawn(sizeX, sizeY, occupiedSpace.getOccupiedSpaces());
                occupiedSpace.addOccupiedSpace(fruits.get(indexOfEaten).getOccupiedSpace());
            }
        }

        ArrayList<Integer> snakesInCollision = new ArrayList<Integer>();
        for (Position pos : nextPositions) {
            for (Position pos2 : nextPositions) {
                if (pos != pos2) {
                    if (pos.equals(pos2)) {
                        snakesInCollision.add(nextPositions.indexOf(pos));
                    }
                }
            }
        }

        for (Integer i : snakesInCollision) {
            snakes.get(i).die();
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

    public ArrayList<Snake> getSnakes() {
        return snakes;
    }
}
