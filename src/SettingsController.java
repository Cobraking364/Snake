package src;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class SettingsController extends Controller {
    private SettingsView view;
    private Scene scene;

    public SettingsController(SettingsView view, Scene scene, Settings settings, SceneManager SceneManager) {
        super(settings, SceneManager);
        this.view = view;
        this.scene = scene;

        view.getBackButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                MainMenuView mainMenuView = new MainMenuView((int) view.getWidth(), (int) view.getHeight());
                Scene mainMenuScene = new Scene(mainMenuView);
                MainMenuController mainMenuController = new MainMenuController(mainMenuView, mainMenuScene,
                    getSettings(), getSceneManager());
                    SettingsHandler handler = new SettingsHandler();
                handler.saveSettings(settings);
                getSceneManager().changeScene(mainMenuScene);
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
