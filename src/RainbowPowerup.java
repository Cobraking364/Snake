package src;

public class RainbowPowerup extends SnakePowerup {

    RainbowPowerup(Position position) {
        super(position);
    }


    @Override
    public void use(Snake snake) {
        snake.changeState(new RainbowState());
    }

}
