package src;

import java.util.List;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    private int windowWidth = 600;
    private int windowHeight = 600;
    private int sizeX;
    private int sizeY;
    private boolean parsingFailed;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        var params = getParameters().getRaw();
        if (params.size() != 2) {
            System.out.println("Must have excactly two parameters. Using saved or default values.");
            parsingFailed = true;
            return;
        }

        try {
            sizeX = Integer.parseInt(params.get(0));
            sizeY = Integer.parseInt(params.get(1));
        } catch (Exception e) {
            System.out.println("Parameters must be integers. Using saved or default values.");
            parsingFailed = true;
        }
    }

    @Override
    public void start(Stage stage) {
        SettingsHandler handler = new SettingsHandler();
        Settings settings = new Settings(sizeX, sizeY);
        handler.loadSettings(settings);
        if (!parsingFailed) {
            List<String> parameters = getParameters().getRaw();
            settings.setSizeX(Math.clamp(sizeX, 5, 100));
            settings.setSizeY(Math.clamp(sizeY, 5, 100));
            handler.saveSettings(settings);
        }

        SceneManager sceneManager = new SceneManager(stage);
        SoundManager soundManager = new SoundManager();
        Font.loadFont(
                getClass().getResource("/resources/PressStart2P-Regular.ttf").toExternalForm(),
                16);

        sceneManager.changeToMainMenu(windowWidth, windowHeight, settings, soundManager);
        Image icon = new Image(getClass().getResource("/resources/Snake.png").toExternalForm());
        stage.getIcons().add(icon);
        stage.setResizable(true);
        stage.setTitle("Snake");
        stage.show();
        soundManager.playSound(Sounds.BOOTUP, settings.getSoundVolume());
    }
}
