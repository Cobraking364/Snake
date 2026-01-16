package src;

import java.util.ArrayList;

public class Grounded extends SnakeState{
    @Override
    public boolean checkCollision(Snake snake, Position position, ArrayList<Snake> otherSnakes) {
        for (Snake iteSnake : otherSnakes) {
            if (iteSnake != snake) {
                for (int i = 0; i < iteSnake.getBody().size(); i++) {
                    if (iteSnake.getBody().get(i).equals(position)) {
                        return true;
                    }
                }
            } else{
                for (int i = 1; i < iteSnake.getBody().size() - 2; i++) {
                    if (iteSnake.getBody().get(i).equals(position)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void update(Snake snake, double deltaTime){

    }

    public void jump(Snake snake){
        snake.changeState(new Airborne(snake.getJumpLength()));
        snake.setHasJumped(true);
    }

}
