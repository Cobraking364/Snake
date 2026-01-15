package src;

public class RainbowState extends SnakeState{

    private final double MAX_DURATION = 16;
    private double currentDuration;

    RainbowState() {
        currentDuration = MAX_DURATION;
    }

    
    @Override
    public void update(Snake snake, double deltaTime) {
        currentDuration -= deltaTime;
        if (currentDuration <= 0) {
            snake.changeState(new Grounded());
        }
    }


    @Override
    public void jump(Snake snake) {
        
    }

}
