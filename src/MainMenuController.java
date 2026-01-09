package src;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class MainMenuController extends Controller{
    private MainMenuView view;
    private Scene scene;

    public MainMenuController(MainMenuView view, Scene scene, Settings settings, SceneManager sceneManager) {
        super(settings, sceneManager);
        this.view = view;
        this.scene = scene;

        view.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameView gameView = new GameView(settings.getSizeX(), settings.getSizeY());
                Scene gameViewScene = new Scene(gameView);
                Board board = new Board(settings.getSizeX(), settings.getSizeY());
                getSceneManager().changeScene(gameViewScene);
                GameController gameController = new GameController(gameView, gameViewScene, board, settings, sceneManager);
            }
        });

        view.getQuitButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }

        });

        view.getSettingsButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SettingsView settingsView = new SettingsView((int)view.getWidth(),(int)view.getHeight(), getSettings());
                Scene settingsScene = new Scene(settingsView);
                getSceneManager().changeScene(settingsScene);
                SettingsController settingsController = new SettingsController(settingsView, settingsScene, settings, sceneManager);
            }

        });
    }
}