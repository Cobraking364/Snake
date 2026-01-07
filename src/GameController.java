package src;

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
                    MainMenuView mainMenuView = new MainMenuView((int) view.getWidth(), (int) view.getHeight());
                    Scene mainMenuScene = new Scene(mainMenuView);
                    MainMenuController mainMenuController = new MainMenuController(mainMenuView, mainMenuScene,
                            sceneManager);

                    sceneManager.changeScene(mainMenuScene);
                }

            }

        });
    }

    private void updateView() {
        view.drawBackground(board.getSizeX(), board.getSizeY());
        view.drawFruit(board.getFruit().getPosition(), board.getSizeX(), board.getSizeY());
        view.drawSnake(board.getSnake().getBody(), board.getSizeX(), board.getSizeY());
    }
}