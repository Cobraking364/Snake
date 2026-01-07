package src;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainMenuView extends StackPane {
    private final int BUTTON_SPACING = 25;
    private VBox buttonContainer;
    private Button startButton;
    private Button quitButton;

    MainMenuView(int width, int height) {
        startButton = new Button("Start");
        quitButton = new Button("Quit");
        
        
        buttonContainer = new VBox(BUTTON_SPACING);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.getChildren().addAll(startButton, quitButton);
        
        getChildren().add(buttonContainer);
        setPrefSize(width, height);
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getQuitButton() {
        return quitButton;
    }
}
