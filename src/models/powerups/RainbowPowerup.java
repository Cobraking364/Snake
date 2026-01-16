package src.models.powerups;

import src.models.Position;
import src.models.Snake;
import src.models.snakeState.RainbowState;

public class RainbowPowerup extends SnakePowerup {

    public RainbowPowerup(Position position) {
        super(position);
    }


    @Override
    public void use(Snake snake) {
        snake.changeState(new RainbowState());
    }

}
