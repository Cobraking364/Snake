package src;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class GameOverController extends Controller {
    private GameOverView view;

    public GameOverController(GameOverView view, Settings settings, SceneManager sceneManager) {
        super(settings, sceneManager);
        this.view = view;
        view.getRestartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getSceneManager().newGame(getSettings());
                SoundManager.playSound(Sounds.START, getSettings().getSoundVolume());
            }
        });

        view.getQuitButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SoundManager.playSound(Sounds.CLICK, getSettings().getSoundVolume());
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
                SoundManager.playSound(Sounds.CLICK, getSettings().getSoundVolume());
            }
        });
    }

}
