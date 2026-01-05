package src;
public class Board {
    private int sizeX;
    private int sizeY;
    private Snake snake;
    private Fruit fruit;

    Board(int x, int y){
        sizeX = x;
        sizeY = y;
    }

    public int getSizeX(){
        return sizeX;
    }

    public int getSizeY(){
        return sizeY;
    }

    public void update(){
        snake.move();
        for(int i = 0; i < snake.getBody().size()-2; i++){
            if(snake.getBody().get(i).equals(snake.getHead())){
                gameOver();
            }
        }
        if(fruit.getPosition().equals(snake.getHead())){
            snake.grow();
        }
    }

    private void gameOver(){

    }
}
