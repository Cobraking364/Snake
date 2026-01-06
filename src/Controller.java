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
                view.update();
            }

        });
    }
}