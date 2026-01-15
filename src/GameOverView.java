package src;

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class GameOverView extends StackPane {
    private final int BUTTON_SPACING = 25;
    private VBox buttonContainer;
    private Button restartButton;
    private Button quitButton;
    private Button mainMenuButton;

    GameOverView(int yourScore,int highScore, Settings settings) {
        Rectangle blackBackground = new Rectangle();
        blackBackground.heightProperty().bind(heightProperty());
        blackBackground.widthProperty().bind(widthProperty());
        fadeIn(blackBackground);
        redToBlack(blackBackground);

        getChildren().add(blackBackground);

        restartButton = new MenuButton("Restart");
        quitButton = new MenuButton("Quit");
        mainMenuButton = new MenuButton("Mainmenu");
        Label gameOverLabel = new Label("Game over");
        
        String x = ""+settings.getSizeX();
        String y = ""+settings.getSizeY();
        Label boardLabel = new Label(x + " X " + y);

        HighScoreModule highScoreBox = new HighScoreModule("HighScore", 0, highScore);
        HighScoreModule yourScoreBox = new HighScoreModule("Your Score", 0, yourScore);
        gameOverLabel.setId("title-label");
        
        buttonContainer = new VBox(BUTTON_SPACING);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.getChildren().addAll(gameOverLabel,boardLabel, highScoreBox, yourScoreBox, restartButton, mainMenuButton, quitButton);

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

    private void fadeIn(Node node) {
        FadeTransition transition = new FadeTransition(Duration.seconds(2), node);
        transition.setFromValue(0.4);
        transition.setToValue(0.8);
        transition.setInterpolator(Interpolator.EASE_OUT);
        transition.play();
    }

    private void redToBlack(Shape shape) {
        FillTransition transition = new FillTransition(Duration.seconds(2.5), shape);
        transition.setFromValue(Color.RED);
        transition.setToValue(Color.BLACK);
        transition.setInterpolator(Interpolator.EASE_OUT);
        transition.play();
    }
}