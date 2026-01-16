package src.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MultiplayerWinScreenView extends StackPane {
    private Button quitButton;
    private Button mainMenuButton;
    private Button playAgainButton;
    private VBox buttonContainer;
    private final int BUTTON_SPACING = 25;
    private String winColor;

    public MultiplayerWinScreenView(String wintext){

        Image pokal = new Image(getClass().getResource("/resources/Pokal1.png").toExternalForm(),200,200,false,false);
        ImageView selelctedImage = new ImageView();
        selelctedImage.setImage(pokal);
        
        quitButton = new MenuButton("Quit");
        mainMenuButton = new MenuButton("Main menu");
        playAgainButton = new MenuButton("Play Again");
        Label winScreenLabel = new Label(wintext);

        winScreenLabel.setId("wintitle-label");
        buttonContainer = new VBox(BUTTON_SPACING);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.getChildren().addAll(selelctedImage,winScreenLabel, playAgainButton, mainMenuButton, quitButton);
        
        getChildren().add(buttonContainer);
    }

    public Button getPlayAgainButton() {
        return playAgainButton;
    }

    public Button getQuitButton() {
        return quitButton;
    }

    public Button getMainMenuButton() {
        return mainMenuButton;
    }
}

