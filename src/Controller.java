package src;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {
    private Main view;
    private Scene scene;
    private Board board;

    Controller(Main view, Scene scene, Board board) {
        this.view = view;
        this.scene = scene;
        this.board = board;
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                boolean validInput = false;
                switch (event.getCode()) {
                    case KeyCode.LEFT:
                        validInput = board.getSnake().updateDir(Direction.LEFT);
                        break;
                    case KeyCode.RIGHT:
                        validInput = board.getSnake().updateDir(Direction.RIGHT);
                        break;
                    case KeyCode.UP:
                        validInput = board.getSnake().updateDir(Direction.UP);
                        break;
                    case KeyCode.DOWN:
                        validInput = board.getSnake().updateDir(Direction.DOWN);
                        break;

                    default:
                        return;
                }
                if (!validInput) {
                    return;
                }
                board.update();
                view.update();
            }

        });
    }
}