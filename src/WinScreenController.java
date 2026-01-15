package src;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class WinScreenController extends Controller{
    private WinScreenView view;
    
    public WinScreenController(WinScreenView view, Settings settings, SceneManager sceneManager){
        super(settings, sceneManager);
        this.view = view;
        view.getPlayAgainButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameView gameView = new GameView(getSettings().getSizeX(), getSettings().getSizeY());
                Scene gameViewScene = new Scene(gameView);
                Board board = new Board(getSettings().getSizeX(), getSettings().getSizeY(), getSettings().getFruitCount(), getSettings().getPlayerCount());
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
                MainMenuController mainMenuController = new MainMenuController(mainMenuView, mainMenuScene, getSettings(), getSceneManager());
            }
        });

    }
}
