package src;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PauseScreenView extends StackPane{
    private Button quitButton;
    private Button mainMenuButton;
    private Button resume;
    private Button restartButton;
    private VBox buttonContainer;
    private final int BUTTON_SPACING = 25;

    public PauseScreenView(int width, int height){
        quitButton = new MenuButton("Quit");
        mainMenuButton = new MenuButton("Main menu");
        restartButton = new MenuButton("Restart");
        resume = new MenuButton("Resume");
        Label pauseScreenLabel = new Label("Paused");
        getStylesheets().add(getClass().getResource("/resources/menu.css").toExternalForm());
        pauseScreenLabel.setId("title-label");
        buttonContainer = new VBox(BUTTON_SPACING);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.getChildren().addAll(pauseScreenLabel, resume, mainMenuButton, quitButton);
        

        getChildren().add(buttonContainer);

        setPrefSize(width, height);

    }
    public Button getResumeButton() {
        return resume;
    }

    public Button getQuitButton() {
        return quitButton;
    }

    public Button getMainMenuButton() {
        return mainMenuButton;
    }

    public Button getRestarButton() {
        return restartButton;
    }
}

