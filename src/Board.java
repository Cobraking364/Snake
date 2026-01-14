package src;

import java.util.*;

public class Board {
    private int sizeX;
    private int sizeY;
    private ArrayList<Fruit> fruits;
    private ArrayList<Snake> snakes;
    private boolean isGameOver;
    private OccupiedSpace occupiedSpace;
    private ArrayList<SnakePowerup> powerups;
    private double powerupChance;

    Board(int x, int y, int amountOfFruits, int amountOfSnakes) {
        isGameOver = false;
        sizeX = x;
        sizeY = y;
        occupiedSpace = new OccupiedSpace();
        snakes = new ArrayList<Snake>();
        powerupChance = 0.05;
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
            Fruit fruit = new Fruit();
            fruits.add(fruit);
            fruit.respawn(getAvailablePosition(occupiedSpace.getOccupiedSpaces()));
            occupiedSpace.addOccupiedSpace(fruit.getOccupiedSpace());
        }

        powerups = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            spawnPowerup();
        }
    }

    public Position getAvailablePosition(List<Position> occupiedSpace) {
        List<Position> possiblePositions = new ArrayList<>();
        for (int i = 0; i < getSizeX(); i++) {
            for (int j = 0; j < getSizeY(); j++) {
                possiblePositions.add(new Position(i, j));
            }
        }
        int listIndex = 0;
        int maxIndex = getSizeX() * getSizeY() - 1;
        Collections.shuffle(possiblePositions);
        while (isInList(possiblePositions.get(listIndex), occupiedSpace)) {
            if (listIndex >= maxIndex) {
                return new Position(-1, -1);
            }
            listIndex++;
        }
        return possiblePositions.get(listIndex);

    }

    private boolean isInList(Position position, List<Position> list) {
        for (Position position2 : list) {
            if (position.equals(position2)) {
                return true;
            }
        }
        return false;
    }

    public void update(double deltaTime) {
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

            // Fruit collision
            int indexOfEaten = -1;
            for (int i = 0; i < getFruits().size(); i++) {
                boolean isEatingFruit = snake.canEatFruit() && getFruits().get(i).getPosition().equals(nextPosition);
                if (isEatingFruit) {
                    snake.grow();
                    indexOfEaten = i;
                }
            }

            // Powerup collision
            for (SnakePowerup powerup : getPowerups()) {
                boolean isPickinUpPowerUp = snake.canEatFruit() && powerup.getPosition().equals(nextPosition);
                if (isPickinUpPowerUp) {
                    powerup.use(snake);
                    occupiedSpace.removeOccupiedSpaces(powerup.getOccupiedSpace());
                    getPowerups().remove(powerup);

                }
            }

            // Moving snake
            if (snake.getLivingStatus()) {
                occupiedSpace.removeOccupiedSpaces(snake.getOccupiedSpace());
                snake.move(nextPosition, deltaTime);
                occupiedSpace.addOccupiedSpace(snake.getOccupiedSpace());
            }

            // Respawn fruit
            if (indexOfEaten != -1) {
                occupiedSpace.removeOccupiedSpace(fruits.get(indexOfEaten).getPosition());
                getFruits().get(indexOfEaten).respawn(getAvailablePosition(occupiedSpace.getOccupiedSpaces()));
                occupiedSpace.addOccupiedSpace(fruits.get(indexOfEaten).getOccupiedSpace());
            }

        }
    }

    public void spawnPowerup() {
        SnakePowerup powerup = new SkipPowerUp(getAvailablePosition(occupiedSpace.getOccupiedSpaces()));
        occupiedSpace.addOccupiedSpace(powerup.getOccupiedSpace());
        getPowerups().add(powerup);
    }

    public ArrayList<SnakePowerup> getPowerups() {
        return powerups;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
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
