package src;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class GameOverController {
    private GameOverView view;

    public GameOverController(GameOverView view, int gameWidth, int gameHeight, SceneManager sceneManager) {
        this.view = view;
        view.getRestartButton().setOnAction(new EventHandler<ActionEvent>() {
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

        view.getMainMenuButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                MainMenuView mainMenuView = new MainMenuView((int) view.getWidth(), (int) view.getHeight());
                Scene mainMenuScene = new Scene(mainMenuView);
                MainMenuController mainMenuController = new MainMenuController(mainMenuView, mainMenuScene, gameWidth,
                        gameHeight, sceneManager);
                sceneManager.changeScene(mainMenuScene);
            }
        });
    }

}
