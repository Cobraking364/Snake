package src;

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class WinScreenView extends StackPane {
    private Button quitButton;
    private Button mainMenuButton;
    private Button playAgainButton;
    private VBox buttonContainer;
    private final int BUTTON_SPACING = 25;

    public WinScreenView(int yourScore,int highScore, Settings settings){

        Image pokal = new Image(getClass().getResource("/resources/Pokal1.png").toExternalForm(),200,200,false,false);
        ImageView selelctedImage = new ImageView();
        selelctedImage.setImage(pokal);
        
        quitButton = new MenuButton("Quit");
        mainMenuButton = new MenuButton("Mainmenu");
        playAgainButton = new MenuButton("Play Again");
        Label winScreenLabel = new Label("You Win!");
        String x = ""+settings.getSizeX();
        String y = ""+settings.getSizeY();
        Label boardLabel = new Label(x + " X " + y);

        HighScoreModule highScoreBox = new HighScoreModule("HighScore", 0, highScore);
        HighScoreModule yourScoreBox = new HighScoreModule("Your Score", 0, yourScore);

        winScreenLabel.setId("wintitle-label");
        buttonContainer = new VBox(BUTTON_SPACING);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.getChildren().addAll(selelctedImage,winScreenLabel,boardLabel,highScoreBox,yourScoreBox, playAgainButton, mainMenuButton, quitButton);
        

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
