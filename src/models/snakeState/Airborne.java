package src;

public class Airborne extends SnakeState{
    private int jumpLength;
    Airborne(int jumpLength){
        this.jumpLength = jumpLength;
    }

    @Override
        public boolean canChangeDirection(Direction newDir, Direction currentDir) {
        return newDir == currentDir;
    }
    
    private int jumpCheck = 0;

    @Override
    public void update(Snake snake, double deltaTime){
        if(jumpCheck == jumpLength){
            snake.changeState(new Grounded());
        }else{
            jumpCheck++;
        }
    }

    @Override
    public boolean canEatFruit(){
        return false;
    }

    @Override
    public void jump(Snake snake) {
        
    }

    @Override
    public void die(Snake snake){

    }
}
