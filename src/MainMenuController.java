package src;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class MainMenuController {
    private MainMenuView view;
    private Scene scene;

    public MainMenuController(MainMenuView view, Scene scener, int gameWidth, int gameHeight, SceneManager sceneManager) {
        this.view = view;
        this.scene = scene;

        view.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameView gameView = new GameView(gameWidth, gameHeight);
                Scene gameViewScene = new Scene(gameView);
                Board board = new Board(gameWidth, gameHeight);
                sceneManager.changeScene(gameViewScene);
                GameController gameController = new GameController(gameView, gameViewScene, board, sceneManager);
            }
        });

        view.getQuitButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }

        });
    }
}