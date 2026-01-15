package src;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class SettingsView extends StackPane{
    private final int BUTTON_SPACING = 25;
    private VBox container;
    private Button backButton;
    private SettingsSliderModule soundSlider;
    private SettingsSliderModule widthSlider;
    private SettingsSliderModule heightSlider;
    private SettingsSliderModule snakeSpeedSlider;
    private SettingsSliderModule playerCountSlider;
    private SettingsSliderModule fruitCountSlider;

    public SettingsView(int windowWidth, int windowheight, Settings settings){        
        soundSlider = new SettingsSliderModule("Sound volume", 0, 100, settings.getSoundVolume(), BUTTON_SPACING);
        widthSlider = new SettingsSliderModule("Game width", 5, 100, settings.getSizeX(), BUTTON_SPACING);
        heightSlider = new SettingsSliderModule("Game height", 5, 100, settings.getSizeY(), BUTTON_SPACING);
        snakeSpeedSlider = new SettingsSliderModule("Snake speed", 4, 20, settings.getSnakeSpeed(), BUTTON_SPACING);
        playerCountSlider = new SettingsSliderModule("Player count", 1, 4, settings.getPlayerCount(), BUTTON_SPACING);
        fruitCountSlider = new SettingsSliderModule("Fruit count", 1, 20, settings.getFruitCount(), BUTTON_SPACING);
        backButton = new MenuButton("Back");
        Label settingsLabel = new Label("Settings");
        settingsLabel.setId("title-label");


        getStylesheets().add(getClass().getResource("/resources/menu.css").toExternalForm());


        container = new VBox(BUTTON_SPACING);
        container.setPadding(new Insets(BUTTON_SPACING));
        container.setAlignment(Pos.CENTER);
        container.getChildren().addAll(settingsLabel, soundSlider, widthSlider, heightSlider, snakeSpeedSlider, playerCountSlider, fruitCountSlider, backButton);
        
        getChildren().add(container);
        setPrefSize(windowWidth, windowheight);
    }


    public SettingsSliderModule getWidthSlider() {
        return widthSlider;
    }

    public SettingsSliderModule getHeightSlider() {
        return heightSlider;
    }

    public SettingsSliderModule getSoundSlider() {
        return soundSlider;
    }

    public SettingsSliderModule getPlayerCountSlider() {
        return playerCountSlider;
    }

    public SettingsSliderModule getFruitCounterSlider() {
        return fruitCountSlider;
    }

    public SettingsSliderModule getSnakeSpeedSlider() {
        return snakeSpeedSlider;
    }

    public Button getBackButton() {
        return backButton;
    }
}
