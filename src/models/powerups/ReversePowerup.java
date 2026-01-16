package src;

import java.util.Collections;

public class ReversePowerup extends SnakePowerup{

    ReversePowerup(Position position) {
        super(position);
    }

    @Override
    public void use(Snake snake) {
                Collections.reverse(snake.getBody());

        Position snakeHead = snake.getHead();
        Position firstBody = snake.getBody().get(snake.getBody().size() - 2);

        
        Direction newDirection;
        if (snakeHead.getX() != firstBody.getX()) {
            newDirection = (snakeHead.getX() - firstBody.getX()) == 1 ? Direction.RIGHT : Direction.LEFT;
        } else {
            newDirection = (snakeHead.getY() - firstBody.getY()) == 1 ? Direction.DOWN : Direction.UP;
        }
        snake.setDirection(newDirection);
        snake.setPreviousDirection(newDirection);
        
    }

}
