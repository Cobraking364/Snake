package src;

import javafx.application.Platform;
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
        Platform.runLater(this::updateView);
        
        scene.getWindow().widthProperty().addListener(windowSizeListener);
        scene.getWindow().heightProperty().addListener(windowSizeListener);
        
        GameLoop gameLoop = new GameLoop() {
            @Override
            public void update(double deltaTime) {
                board.update();
                draw();
            }
        };
        gameLoop.start();

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
        draw();
    }

    private void draw() {
        view.drawBackground(board.getSizeX(), board.getSizeY());
        view.drawFruit(board.getFruit().getPosition(), board.getSizeX(), board.getSizeY());
        view.drawSnake(board.getSnake().getBody(), board.getSizeX(), board.getSizeY());
    }
}