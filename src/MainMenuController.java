package src;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class MainMenuController extends Controller{
    private MainMenuView view;
    private Scene scene;

    public MainMenuController(MainMenuView view, Scene scene, Settings settings, SceneManager sceneManager, SoundManager soundManager) {
        super(settings, sceneManager, soundManager);
        this.view = view;
        this.scene = scene;

        view.getStartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getSceneManager().newGame(getSettings(), getSoundManager());
                soundManager.playSound(Sounds.START, getSettings().getSoundVolume());
            }
        });

        view.getQuitButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                soundManager.playSound(Sounds.CLICK, getSettings().getSoundVolume());
                Platform.exit();
            }

        });

        view.getSettingsButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getSceneManager().changeToSettings((int) view.getWidth(), (int) view.getHeight(), getSettings(), getSoundManager());
                soundManager.playSound(Sounds.CLICK, getSettings().getSoundVolume());
            }

        });
    }
}