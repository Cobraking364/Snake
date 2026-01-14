package src;

public class Board{
    private int sizeX;
    private int sizeY;
    private Snake snake;
    private Fruit fruit;
    private boolean hasEaten;
    private boolean isAlive;
    private boolean hasCollided;
    private boolean isGameOver;

    Board(int x, int y) {
        hasCollided = false;
        hasEaten = false;
        isAlive = true;
        isGameOver = false;
        sizeX = x;
        sizeY = y;

        snake = new Snake(new Position(sizeX / 2, sizeY / 2), new Grounded());
        fruit = new Fruit();
        fruit.respawn(sizeX, sizeY, snake.getBody());
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
        hasEaten = false;
        hasCollided = false;

        Position nextPosition = snake.getNextPosition();

        nextPosition.setX((nextPosition.getX() + sizeX) % sizeX);
        nextPosition.setY((nextPosition.getY() + sizeY) % sizeY);

        if (snake.checkCollision(nextPosition)) {
            gameOver();
            isAlive = false;
            return;
        }

        boolean isEatingFruit = snake.canEatFruit() && fruit.getPosition().equals(nextPosition);
        if (isEatingFruit) {
            snake.grow();
            hasEaten = true;
        }
        snake.move(nextPosition);

        if (isEatingFruit) {
            fruit.respawn(sizeX, sizeY, snake.getBody());
        }

    }

    private void gameOver() {
        isGameOver = true;
        hasCollided = true;
    }

    public boolean getIsGameOver() {
        return isGameOver;
    }

    public Fruit getFruit() {
        return fruit;
    }

    public Snake getSnake() {
        return snake;
    }
    public boolean getHasEaten(){
        return hasEaten;
    }
    public boolean getIsAlive(){
        return isAlive;
    }
}
