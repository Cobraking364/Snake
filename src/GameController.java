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
                        board.getSnakes().get(0).updateDir(Direction.LEFT);
                        break;
                    case KeyCode.RIGHT:
                        board.getSnakes().get(0).updateDir(Direction.RIGHT);
                        break;
                    case KeyCode.UP:
                        board.getSnakes().get(0).updateDir(Direction.UP);
                        break;
                    case KeyCode.DOWN:
                        board.getSnakes().get(0).updateDir(Direction.DOWN);
                        break;
                    case KeyCode.W:
                        board.getSnakes().get(1).updateDir(Direction.UP);
                    case KeyCode.A:
                        board.getSnakes().get(1).updateDir(Direction.LEFT);
                    case KeyCode.SPACE:
                        board.getSnakes().get(0).jump();

                }
                boolean shouldUpdate = false;
                for (Snake snake : board.getSnakes()) {
                    if (snake.getTurned()) {
                        shouldUpdate = true;
                    }
                }
                if (shouldUpdate) {
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
        for (Fruit fruit : board.getFruits()) {
            view.drawFruit(fruit.getPosition(), board.getSizeX(), board.getSizeY());
        }
        for (Snake snake : board.getSnakes()) {
            view.drawSnake(snake.getBody(), board.getSizeX(), board.getSizeY());
        }
    }
}