package src.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import src.models.Settings;

public class WinScreenView extends StackPane {
    private Button quitButton;
    private Button mainMenuButton;
    private Button playAgainButton;
    private VBox buttonContainer;
    private final int BUTTON_SPACING = 16;

    public WinScreenView(int yourScore,int highscore, Settings settings){

        Image pokal = new Image(getClass().getResource("/resources/Pokal1.png").toExternalForm(),200,200,false,false);
        ImageView selelctedImage = new ImageView();
        selelctedImage.setImage(pokal);
        
        quitButton = new MenuButton("Quit");
        mainMenuButton = new MenuButton("Main menu");
        playAgainButton = new MenuButton("Restart");
        Label winScreenLabel = new Label("You Win!");
        Rectangle blackBackground = new Rectangle();
        blackBackground.setFill(Color.BLACK);
        blackBackground.setOpacity(0.5);
        blackBackground.heightProperty().bind(heightProperty());
        blackBackground.widthProperty().bind(widthProperty());
        getChildren().add(blackBackground);
        String x = ""+settings.getSizeX();
        String y = ""+settings.getSizeY();
        Label boardLabel = new Label(x + " x " + y);
        boardLabel.setId("highscore-label");

        //Force git to accept
        HighScoreModule highscoreBox = new HighScoreModule("Highscore", highscore);
        HighScoreModule yourScoreBox = new HighScoreModule("Your score", yourScore);

        winScreenLabel.setId("wintitle-label");
        buttonContainer = new VBox(BUTTON_SPACING);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.getChildren().addAll(selelctedImage,winScreenLabel,boardLabel,highscoreBox,yourScoreBox, playAgainButton, mainMenuButton, quitButton);
        

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
