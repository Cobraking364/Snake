package src;

import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameController {

    private GameView view;
    private Scene scene;
    private Board board;

    GameController(GameView view, Scene scene, Board board, SceneManager sceneManager) {
        this.view = view;
        this.scene = scene;
        this.board = board;
        updateView();

        ChangeListener<Number> windowSizeListener = (observable, oldValue, newValue) ->
            this.updateView();

        scene.getWindow().widthProperty().addListener(windowSizeListener);
        scene.getWindow().heightProperty().addListener(windowSizeListener);

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
                    case KeyCode.SPACE:
                        board.getSnake().jump();

                }

                if (board.getSnake().getTurned()) {
                    board.update();
                    updateView();
                }

                if (board.getIsGameOver()) {
                    GameOverView gameOverView = new GameOverView();
                    GameOverController gameOverController = new GameOverController(gameOverView, board.getSizeX(), board.getSizeY(), sceneManager);
                

                    view.getChildren().add(gameOverView);
                }

            }

        });
    }

    private void updateView() {
        view.updateTileSize();
        view.drawBackground(board.getSizeX(), board.getSizeY());
        view.drawFruit(board.getFruit().getPosition(), board.getSizeX(), board.getSizeY());
        view.drawSnake(board.getSnake().getBody(), board.getSizeX(), board.getSizeY());
    }
}