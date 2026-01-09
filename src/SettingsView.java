package src;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class SettingsView extends StackPane{
    private final int BUTTON_SPACING = 25;
    private VBox container;
    private Button backButton;
    private SettingsSliderModule widthSlider;
    private SettingsSliderModule heightSlider;
    private SettingsSliderModule soundSlider;
    private SettingsSliderModule playerCountSlider;
    private SettingsSliderModule fruitCountSlider;

    public SettingsView(int windowWidth, int windowheight, Settings settings){        
        widthSlider = new SettingsSliderModule("Game width", 5, 100, settings.getSizeX(), BUTTON_SPACING);
        heightSlider = new SettingsSliderModule("Game height", 5, 100, settings.getSizeY(), BUTTON_SPACING);
        soundSlider = new SettingsSliderModule("Sound volume", 0, 100, settings.getSoundVolume(), BUTTON_SPACING);
        playerCountSlider = new SettingsSliderModule("Player count", 1, 4, settings.getSoundVolume(), BUTTON_SPACING);
        fruitCountSlider = new SettingsSliderModule("Fruit count", 1, 20, settings.getSoundVolume(), BUTTON_SPACING);

        backButton = new Button("Back");

        container = new VBox(BUTTON_SPACING);
        container.setPadding(new Insets(BUTTON_SPACING));
        container.setAlignment(Pos.CENTER);
        container.getChildren().addAll(widthSlider, heightSlider, soundSlider, playerCountSlider, fruitCountSlider, backButton);
        
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

    public Button getBackButton() {
        return backButton;
    }
}
