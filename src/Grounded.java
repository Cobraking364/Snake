package src;

public class Grounded extends SnakeState{
    @Override
    public boolean checkCollision(Snake snake) {
        for(int i = 0; i < snake.getBody().size()-2; i++){
            if(snake.getBody().get(i).equals(snake.getHead())){
                return true;
            }
        }
        return false;
    }
}
