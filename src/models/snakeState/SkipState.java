package src;

import java.util.ArrayList;

public class SkipState extends SnakeState{

    
    private final double MAX_DURATION = 12;
    private double currentDuration;

    SkipState() {
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
