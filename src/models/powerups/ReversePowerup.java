package src.models.powerups;

import java.util.Collections;
import src.models.Direction;
import src.models.Position;
import src.models.Snake;

public class ReversePowerup extends SnakePowerup{

    public ReversePowerup(Position position) {
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
