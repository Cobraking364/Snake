package src.models;

import java.util.*;

import src.models.powerups.RainbowPowerup;
import src.models.powerups.ReversePowerup;
import src.models.powerups.SkipPowerUp;
import src.models.powerups.SnakePowerup;
import src.models.snakeState.Grounded;

public class Board {

    private int sizeX;
    private int sizeY;
    private Snake snake;
    private Fruit fruit;
    private boolean hasEaten;
    private boolean isAlive;
    private boolean hasCollided;
    private ArrayList<Fruit> fruits;
    private ArrayList<Snake> snakes;
    private boolean isGameOver;
    private boolean isGameWon;
    private OccupiedSpace occupiedSpace;
    private ArrayList<SnakePowerup> powerups;
    private double powerupChance;
    private final double BASE_POWERUP_CHANCE = 0.05;
    private final double POWERUP_CHANCE_INCREASE = 0.1;
    private final int POWER_UP_COUNT = 3;
    private boolean isGameMultiplayer;
    private int winnerIndex;
    private int score;

    public Board(int x, int y, int amountOfFruits, int amountOfSnakes) {
        isGameOver = false;
        isGameWon = false;
        sizeX = x;
        sizeY = y;
        occupiedSpace = new OccupiedSpace();
        snakes = new ArrayList<Snake>();
        powerupChance = BASE_POWERUP_CHANCE;
        isGameMultiplayer = amountOfSnakes > 1;
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
    }

    public void update(double deltaTime) {
        if (isGameOver || isGameWon) {
            return;
        }

        hasEaten = false;
        hasCollided = false;

        if (getAliveCount() == 0 && !isGameMultiplayer) {
            gameOver();
            return;
        }

        if (getAliveCount() <= 1 && isGameMultiplayer) {
            win();
            return;
        }

        if (!isGameMultiplayer && sizeX * sizeY - getPowerups().size() <= getSnakes().get(0).getBody().size()) {
            win();
            return;
        }

        HashMap<Snake, Position> nextPositions = getNextPositions();
        ArrayList<Snake> snakesInCollision = new ArrayList<Snake>();

        // Check colliding heads
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
            hasCollided = true;
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
                hasCollided = true;
                snake.die();
            }
            
            // Fruit collision
            int indexOfEaten = -1;
            for (int i = 0; i < getFruits().size(); i++) {
                boolean isEatingFruit = snake.canEatFruit() && getFruits().get(i).getPosition().equals(nextPosition);
                if (isEatingFruit) {
                    snake.grow();
                    indexOfEaten = i;
                    hasEaten = true;
                    rollPowerupSpawn();
                }
            }
            // Moving snake
            if (snake.getLivingStatus()) {
                occupiedSpace.removeOccupiedSpaces(snake.getOccupiedSpace());
                snake.move(nextPosition, deltaTime);
                occupiedSpace.addOccupiedSpace(snake.getOccupiedSpace());
            }


            // Powerup collision
            for (Iterator<SnakePowerup> iterator = getPowerups().iterator(); iterator.hasNext();) {
                SnakePowerup powerup = iterator.next();
                boolean isPickinUpPowerUp = snake.canEatFruit() && powerup.getPosition().equals(nextPosition);
                if (isPickinUpPowerUp) {
                    powerup.use(snake);
                    occupiedSpace.removeOccupiedSpaces(powerup.getOccupiedSpace());
                    iterator.remove();
                }
            }

            // Respawn fruit
            if (indexOfEaten != -1) {
                occupiedSpace.removeOccupiedSpace(fruits.get(indexOfEaten).getPosition());
                getFruits().get(indexOfEaten).respawn(getAvailablePosition(occupiedSpace.getOccupiedSpaces()));
                occupiedSpace.addOccupiedSpace(fruits.get(indexOfEaten).getOccupiedSpace());
            }
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

    public void spawnRandomPowerup() {
        Position spawnPosition = getAvailablePosition(occupiedSpace.getOccupiedSpaces());
        SnakePowerup powerup = switch (new Random().nextInt(POWER_UP_COUNT)) {
            case 1 ->
                new RainbowPowerup(spawnPosition);
            case 2 ->
                new SkipPowerUp(spawnPosition);
            default ->
                new ReversePowerup(spawnPosition);
        };
        occupiedSpace.addOccupiedSpace(powerup.getOccupiedSpace());
        getPowerups().add(powerup);
    }

    private void rollPowerupSpawn() {
        if (new Random().nextDouble() < powerupChance) {
            spawnRandomPowerup();
            powerupChance = BASE_POWERUP_CHANCE;
        } else {
            powerupChance += POWERUP_CHANCE_INCREASE;
        }
    }

    public ArrayList<SnakePowerup> getPowerups() {
        return powerups;
    }

    private int getAliveCount() {
        int count = 0;
        for (Snake snake : snakes) {
            if (snake.getLivingStatus()) {
                count++;
            }
        }
        return count;
    }

    private HashMap<Snake, Position> getNextPositions() {
        HashMap<Snake, Position> nextPositions = new HashMap<>();
        for (Snake snake : snakes) {
            if (snake.getLivingStatus()) {
                Position nextPosition = snake.getNextPosition();
                nextPositions.put(snake, nextPosition);
            }
        }

        return nextPositions;
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

    public boolean getIsGameMultiplayer() {
        return isGameMultiplayer;
    }

    public boolean getIsGameOver() {
        return isGameOver;
    }

    private void win() {
        isGameWon = true;
        winnerIndex = -1;
        for (int i = 0; i < getSnakes().size(); i++) {
            if (getSnakes().get(i).getLivingStatus()) {
                winnerIndex = i;
                break;
            }
        }
    }

    public int getScore() {
        final int STARTING_LENGTH = 2;
        return getSnakes().get(0).getBody().size() - STARTING_LENGTH;
    }

    public int getWinnerIndex() {
        return winnerIndex;
    }

    public boolean getIsgameWon() {
        return isGameWon;
    }

    public ArrayList<Fruit> getFruits() {
        return fruits;
    }

    public ArrayList<Snake> getSnakes() {
        return snakes;
    }

    public boolean getHasEaten() {
        return hasEaten;
    }

    public boolean getHasSnakeJumped() {
        for (int i = 0; i < getSnakes().size(); i++) {
            if (getSnakes().get(i).getHasJumped()) {
                return true;
            }
        }
        return false;
    }

    public boolean getHasCollided() {
        return hasCollided;
    }
}