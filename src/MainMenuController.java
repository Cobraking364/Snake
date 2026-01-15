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

        view.getSettingsButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SettingsView settingsView = new SettingsView((int)view.getWidth(),(int)view.getHeight(), getSettings());
                Scene settingsScene = new Scene(settingsView);
                getSceneManager().changeScene(settingsScene);
                SettingsController settingsController = new SettingsController(settingsView, settingsScene, settings, sceneManager);
                SoundManager.playSound(Sounds.CLICK, getSettings().getSoundVolume());
            }

        });
    }
}