package src.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import src.models.Settings;
import src.models.Sounds;
import src.views.SettingsView;

public class SettingsController extends Controller {
    private SettingsView view;
    private Scene scene;

    public SettingsController(SettingsView view, Scene scene, Settings settings, SceneManager SceneManager, SoundManager soundManager) {
        super(settings, SceneManager, soundManager);
        this.view = view;
        this.scene = scene;

        view.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SettingsHandler handler = new SettingsHandler();
                handler.saveSettings(settings);
                soundManager.playSound(Sounds.CLICK, getSettings().getSoundVolume());
                getSceneManager().changeToMainMenu((int) view.getWidth(), (int) view.getHeight(), getSettings(), getSoundManager());
            }
        });

        view.getWidthSlider().getSliderProperty().addListener((ObservableValue, oldValue, newValue) -> {
            getSettings().setSizeX(newValue.intValue());
        });

        view.getHeightSlider().getSliderProperty().addListener((ObservableValue, oldValue, newValue) -> {
            getSettings().setSizeY(newValue.intValue());
        });

        view.getSoundSlider().getSliderProperty().addListener((ObservableValue, oldValue, newValue) -> {
            getSettings().setSoundVolume(newValue.intValue());
        });
        view.getPlayerCountSlider().getSliderProperty().addListener((ObservableValue, oldValue, newValue) -> {
            getSettings().setPlayerCount(newValue.intValue());
        });
        view.getFruitCounterSlider().getSliderProperty().addListener((ObservableValue, oldValue, newValue) -> {
            getSettings().setFruitCount(newValue.intValue());
        });
        view.getSnakeSpeedSlider().getSliderProperty().addListener((ObservableValue, oldValue, newValue) -> {
            getSettings().setSnakeSpeed(newValue.intValue());
        });
    }

}
