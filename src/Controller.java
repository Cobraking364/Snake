package src;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

    
    
public class Controller {
    private Main view;
    private Scene scene;
    private Board board;

    
    scene.setOnKeyPressed(new EventHandler<KeyEvent>(){

        public void handle(KeyEvent event){
            switch (event.getCode()) {
                case KeyCode.LEFT:
                    board.getSnake().updateDir(Direction.LEFT);
                    break;
                case KeyCode.RIGHT:
                    board.getSnake().updateDir(Direction.RIGHT);
                    break;
                case KeyCode.UP:
                    board.getSnake().updateDir(Direction.UP);
                    break;
                case KeyCode.DOWN:
                    board.getSnake().updateDir(Direction.DOWN);
                    break;
            
                default:
                    return;

                }
            board.update();
   
        }
    });
}
