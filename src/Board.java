package src;

import java.util.*;

public class Board {
    private int sizeX;
    private int sizeY;
    private ArrayList<Fruit> fruits;
    private ArrayList<Snake> snakes;
    private boolean isGameOver;
    private OccupiedSpace occupiedSpace;

    Board(int x, int y, int amountOfFruits, int amountOfSnakes) {
        isGameOver = false;
        sizeX = x;
        sizeY = y;

        occupiedSpace = new OccupiedSpace();
        snakes = new ArrayList<Snake>();
        if (amountOfSnakes == 1) {
            Snake snake = new Snake(new Position(sizeX / 2, sizeY / 2), new Grounded());
            occupiedSpace.addOccupiedSpace(snake.getOccupiedSpace());
            snakes.add(snake);
        } else {
            for (int i = 0; i < amountOfSnakes; i++) {
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
            return;
        }

        HashMap<Snake, Position> nextPositions = new HashMap<>();

        for (Snake snake : snakes) {
            if (snake.getLivingStatus()) {
                Position nextPosition = snake.getNextPosition();
                nextPositions.put(snake, nextPosition);
            }
        }

        ArrayList<Snake> snakesInCollision = new ArrayList<Snake>();

        nextPositions.forEach((firstSnake, firstPosition) -> {
            nextPositions.forEach((secondSnake, secondPosition) -> {
                if (firstPosition != secondPosition) {
                    if (firstPosition.equals(secondPosition)) {
                        snakesInCollision.add(firstSnake);
                    }
                }
            });
        });

        for (Snake snake : snakesInCollision) {
            snake.die();
        }

        for (Snake snake : snakes) {
            if (!snake.getLivingStatus()) {
                continue;
            }

            Position nextPosition = nextPositions.get(snake);
            nextPosition.setX((nextPosition.getX() + sizeX) % sizeX);
            nextPosition.setY((nextPosition.getY() + sizeY) % sizeY);

            if (snake.checkCollision(nextPosition, snakes)) {
                snake.die();
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
            if (snake.getLivingStatus()) {
                snake.move(nextPosition);
            }
            occupiedSpace.addOccupiedSpace(snake.getOccupiedSpace());

            if (indexOfEaten != -1) {
                occupiedSpace.removeOccupiedSpace(fruits.get(indexOfEaten).getPosition());
                fruits.get(indexOfEaten).respawn(sizeX, sizeY, occupiedSpace.getOccupiedSpaces());
                occupiedSpace.addOccupiedSpace(fruits.get(indexOfEaten).getOccupiedSpace());
            }
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
