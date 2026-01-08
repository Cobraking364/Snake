package src;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameOverView extends StackPane{
    private final int BUTTON_SPACING = 25;
    private VBox buttonContainer;
    private Button restartButton;
    private Button quitButton;
    private Button mainMenuButton;
   

    GameOverView() {
        Rectangle blackBackground = new Rectangle();
        blackBackground.setFill(Color.BLACK);
        blackBackground.heightProperty().bind(heightProperty());
        blackBackground.widthProperty().bind(widthProperty());
        blackBackground.setOpacity(0.5);
        getChildren().add(blackBackground);

        
        restartButton = new Button("Restart");
        quitButton = new Button("Quit");
        mainMenuButton = new Button("Mainmenu");
        
        buttonContainer = new VBox(BUTTON_SPACING);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.getChildren().addAll(restartButton, mainMenuButton, quitButton);
        
        getChildren().add(buttonContainer);

        
    }

    public Button getRestartButton() {
        return restartButton;
    }

    public Button getQuitButton() {
        return quitButton;
    }

    public Button getMainMenuButton() {
        return mainMenuButton;
    }
}
