package src;

import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    private int windowWidth = 600;
    private int windowHeight = 600;
    int sizeX;
    int sizeY;

    public static void main(String[] args) {
        // if (args.length != 2) {
        //     throw new IllegalArgumentException("Must be excactly 2 parameters.");
        // }
        // try {
        //     Integer.parseInt(args[0]);
        //     Integer.parseInt(args[1]);
        // } catch (NumberFormatException e) {
        //     throw new IllegalArgumentException("Parameters must be integers.");
        // }

        launch(args);
    }

    @Override
    public void start(Stage stage) {
        List<String> parameters = getParameters().getRaw();
        // sizeX = Integer.parseInt(parameters.get(0))
        sizeX = 12;
        // sizeY = Integer.parseInt(parameters.get(1));
        sizeY = 12;
        sizeX = Math.clamp(sizeX, 5, 100);
        sizeY = Math.clamp(sizeY, 5, 100);
 
        SceneManager sceneManager = new SceneManager(stage);

        MainMenuView mainMenuView = new MainMenuView(windowWidth, windowHeight);
        Scene mainMenuScene = new Scene(mainMenuView);
        Settings settings = new Settings(sizeX, sizeY);
        MainMenuController mainMenuController = new MainMenuController(mainMenuView, mainMenuScene, sizeX, sizeY, settings, sceneManager);

        sceneManager.changeScene(mainMenuScene);
        Image icon  = new Image("src/Snake.png");
        stage.getIcons().add(icon);
        stage.setResizable(true);
        stage.setTitle("Snake");
        stage.show();
    }
}
