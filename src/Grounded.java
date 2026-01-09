package src;

public class Grounded extends SnakeState{
    @Override
    public boolean checkCollision(Snake snake, Position position) {
        for(int i = 1; i < snake.getBody().size()-2; i++){
            if(snake.getBody().get(i).equals(position)){
                return true;
            }
        }
        return false;
    }

    public void jump(Snake snake){
        snake.changeState(new Airborne(snake.getJumpLength()));
    }


}
