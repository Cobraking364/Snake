package src;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class GameOverController extends Controller {
    private GameOverView view;

    public GameOverController(GameOverView view, int gameWidth, int gameHeight, Settings settings, SceneManager sceneManager) {
        super(settings, sceneManager);
        this.view = view;
        view.getRestartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameView gameView = new GameView(gameWidth, gameHeight);
                Scene gameViewScene = new Scene(gameView);
                Board board = new Board(gameWidth, gameHeight);
                getSceneManager().changeScene(gameViewScene);
                GameController gameController = new GameController(gameView, gameViewScene, board, getSettings(), getSceneManager());
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
                getSceneManager().changeScene(mainMenuScene);
                MainMenuController mainMenuController = new MainMenuController(mainMenuView, mainMenuScene, gameWidth,
                        gameHeight, getSettings(), getSceneManager());
            }
        });
    }

}
