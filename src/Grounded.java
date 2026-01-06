package src;

public interface Grounded extends SnakeState{
    @Override
    default boolean checkCollision(Snake snake) {
        for(int i = 0; i < snake.getBody().size()-2; i++){
            if(snake.getBody().get(i).equals(snake.getHead())){
                return true;
            }
        }
        return false;
    }
    
    @Override
    default Direction changeDir(Snake snake, Direction newDir, Direction currentDir) {
        if((newDir.getValue()+2)%4 != currentDir.getValue()){
            return newDir;
        }
        return currentDir;
    }
}
