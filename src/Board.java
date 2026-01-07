package src;
public class Board {
    private int sizeX;
    private int sizeY;
    private Snake snake;
    private Fruit fruit;
    private boolean  isGameOver;

    Board(int x, int y){
        isGameOver = false;
        sizeX = x;
        sizeY = y;

        snake = new Snake(new Position(sizeX/2, sizeY/2));
        fruit = new Fruit();
        fruit.respawn(sizeX, sizeY, snake.getBody());
    }

    public int getSizeX(){
        return sizeX;
    }

    public int getSizeY(){
        return sizeY;
    }

    public void update(){
        if (isGameOver) {
            return;
        }

        Position nextPosition = snake.getNextPosition();

        nextPosition.setX((nextPosition.getX()+sizeX)%sizeX);
        nextPosition.setY((nextPosition.getY()+sizeY)%sizeY);

        if(snake.checkCollision(nextPosition)){
            gameOver();
            return;
        }

        boolean eatsFruit = snake.canEatFruit() && fruit.getPosition().equals(nextPosition);
        
        if(eatsFruit){
            snake.grow();
        }

        snake.move(nextPosition);

        if(eatsFruit){
            fruit.respawn(sizeX, sizeY, snake.getBody());
        }
    }

    private void gameOver(){
        System.out.println("GAME OVER");
        javafx.application.Platform.exit();
    }

    public Fruit getFruit() {
        return fruit;
    }

    public Snake getSnake() {
        return snake;
    }
}
