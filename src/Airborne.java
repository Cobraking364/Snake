package src;

public class Airborne extends SnakeState{
    private int jumpLength;
    Airborne(int jumpLength){
        this.jumpLength = jumpLength;
    }

    @Override
    public boolean checkCollision(Snake snake) {
        return false;
    }

    @Override
    public Direction changeDir(Snake snake, Direction newDir, Direction currentDir){
        return currentDir;
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
