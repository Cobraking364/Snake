package src;
public class Board {
    private int sizeX;
    private int sizeY;
    private Snake snake;
    private Fruit fruit;

    Board(int x, int y){
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
        snake.move();
        snake.getHead().setX((snake.getHead().getX()+sizeX)%sizeX);
        snake.getHead().setY((snake.getHead().getY()+sizeY)%sizeY);

        if(snake.checkCol()){
            gameOver();
        }
        if(fruit.getPosition().equals(snake.getHead())){
            snake.grow();
            fruit.respawn(sizeX, sizeY, snake.getBody());
        }
    }

    private void gameOver(){
        System.out.println("GAME OVER");
    }

    public Fruit getFruit() {
        return fruit;
    }

    public Snake getSnake() {
        return snake;
    }
}
