package src;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class SettingsView extends StackPane{
    private final int BUTTON_SPACING = 25;
    private VBox Container;
    private HBox SliderContainer;
    private HBox labelContainer;
    private Label labelX;
    private Label labelY;
    private Button soundButton;
    private Button backButton;
    private Slider widthSlider;
    private Slider heightSlider;
    private Label labelText;

    public SettingsView(int windowWidth, int windowheight, Settings settings){
        labelX=new Label(""+settings.getSizeX());
        labelY=new Label(""+settings.getSizeY());
        labelContainer = new HBox(BUTTON_SPACING);
        labelContainer.getChildren().addAll(labelX,labelY);
        labelContainer.setAlignment(Pos.CENTER);

        widthSlider = new Slider(5, 100, settings.getSizeX());
        heightSlider = new Slider(5, 100,settings.getSizeY());

        SliderContainer = new HBox(BUTTON_SPACING);
        SliderContainer.getChildren().addAll(widthSlider,heightSlider);
        SliderContainer.setAlignment(Pos.CENTER);
        
        labelText = new Label("X        Y");
        labelText.setAlignment(Pos.CENTER);
        labelText.setMaxWidth(100);
        soundButton = new Button("Sound");
        backButton = new Button("Back");

        Container = new VBox(BUTTON_SPACING);
        Container.setAlignment(Pos.CENTER);
        Container.getChildren().addAll(soundButton,labelText, labelContainer, SliderContainer, backButton);
        
        getChildren().add(Container);
        setPrefSize(windowWidth, windowheight);
    }
    
    public Button getSoundButton() {
        return soundButton;
    }

    public Button getBackButton() {
        return backButton;
    }

    public Slider getWidthSlider() {
        return widthSlider;
    }
    
    public Slider getHeightSlider() {
        return heightSlider;
    }

    public Label getLabelX() {
        return labelX;
    }
    public Label getLabelY() {
        return labelY;
    }
}
