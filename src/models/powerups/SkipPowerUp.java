package src.models.powerups;

import src.models.Position;
import src.models.Snake;
import src.models.snakeState.SkipState;

public class SkipPowerUp extends SnakePowerup{



    public SkipPowerUp(Position position) {
        super(position);
    }

    @Override
    public void use(Snake snake) {
        snake.changeState(new SkipState());
    }

}
