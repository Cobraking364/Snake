package src;

public class Airborne extends SnakeState{
    private int jumpLength;
    Airborne(int jumpLength){
        this.jumpLength = jumpLength;
    }

    @Override
    public boolean checkCollision(Snake snake, Position position) {
        return false;
    }

    @Override
        public boolean canChangeDirection(Direction newDir, Direction currentDir) {
        return newDir == currentDir;
    }
    
    private int jumpCheck = 1;

    @Override
    public void jumpFinished(Snake snake){
        if(jumpCheck == jumpLength){
            snake.endJump();
        }else{
            jumpCheck++;
        }
    }

    @Override
    public boolean canEatFruit(){
        return false;
    }
}
