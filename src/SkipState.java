package src;

public class SkipState extends SnakeState{

    
    private final double MAX_DURATION = 12;
    private double currentDuration;

    SkipState() {
        currentDuration = MAX_DURATION;
    }
    
    @Override
    public void update(Snake snake, double deltaTime) {
        System.out.println(currentDuration);
        currentDuration -= deltaTime;
        if (currentDuration <= 0) {
            snake.changeState(new Grounded());
        }
    }
    @Override
    public void jump(Snake snake) {
    }

    @Override
    public Position getNextPosition(Position currentHeadPos, Direction direction) {
        Position nextPos;
        nextPos = switch (direction) {
            case Direction.UP -> new Position(currentHeadPos.getX(), currentHeadPos.getY() - 2);
            case Direction.LEFT -> new Position(currentHeadPos.getX() - 2, currentHeadPos.getY());
            case Direction.DOWN -> new Position(currentHeadPos.getX(), currentHeadPos.getY() + 2);
            case Direction.RIGHT -> new Position(currentHeadPos.getX() + 2, currentHeadPos.getY());
            default -> new Position(0, 0);
        };

        return nextPos;
    }
}
