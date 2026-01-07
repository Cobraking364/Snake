package src;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class MainMenuController {
    private MainMenuView view;
    private Scene scene;

    public MainMenuController(MainMenuView view, Scene scene, SceneManager sceneManager) {
        this.view = view;
        this.scene = scene;

        view.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameView gameView = new GameView((int) view.getWidth(), (int) view.getHeight(), 3, 3);
                Scene gameViewScene = new Scene(gameView);
                Board board = new Board(3, 3);
                GameController gameController = new GameController(gameView, gameViewScene, board, sceneManager);
                sceneManager.changeScene(gameViewScene);
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