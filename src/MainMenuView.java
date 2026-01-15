package src;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainMenuView extends StackPane {
    private final int BUTTON_SPACING = 25;
    private Button startButton;
    private Button quitButton;
    private Button settingsButton;

    MainMenuView(int width, int height) {
        startButton = new MenuButton("Start");
        quitButton = new MenuButton("Quit");
        settingsButton = new MenuButton("Settings");
        Label titleLabel = new Label("Snake");
        getStylesheets().add(getClass().getResource("/resources/menu.css").toExternalForm());
        titleLabel.setId("title-label");

                
        VBox buttonContainer = new VBox(BUTTON_SPACING);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.getChildren().addAll(titleLabel, startButton, settingsButton, quitButton);
        Image backGroundPicture = new Image(getClass().getResource("/resources/Snake mainmenu background.png").toExternalForm(),600,600,false,false);
        ImageView backGround = new ImageView();
        backGround.setImage(backGroundPicture);

        getChildren().addAll(backGround,buttonContainer);
        setPrefSize(width, height);
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getQuitButton() {
        return quitButton;
    }

    public Button getSettingsButton() {
        return settingsButton;
    }
}
