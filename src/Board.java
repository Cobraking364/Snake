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

        snake.move();
        snake.getHead().setX((snake.getHead().getX()+sizeX)%sizeX);
        snake.getHead().setY((snake.getHead().getY()+sizeY)%sizeY);

        if(snake.checkCol()){
            gameOver();
        }
        if(snake.canEatFruit()){
            if(fruit.getPosition().equals(snake.getHead())){
                snake.grow();
                fruit.respawn(sizeX, sizeY, snake.getBody());
            }
        }
    }

    private void gameOver(){
        System.out.println("GAME OVER");
        isGameOver = true;
    }

    public Fruit getFruit() {
        return fruit;
    }

    public Snake getSnake() {
        return snake;
    }
}
