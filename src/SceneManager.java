package src;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private Stage stage;

    private Controller currentController;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    private void changeScene(Scene scene) {
        stage.setScene(scene);
    }

    public void changeToMainMenu(int width, int height, Settings settings, SoundManager soundManager) {
        MainMenuView mainMenuView = new MainMenuView(width, height);
        Scene mainMenuScene = new Scene(mainMenuView);
        changeScene(mainMenuScene);
        currentController = new MainMenuController(mainMenuView, mainMenuScene, settings,
                this, soundManager);
    }

    public void changeToGame(Settings settings, Board board, SoundManager soundManager) {
        GameView gameView = new GameView(settings.getSizeX(), settings.getSizeY());
        Scene gameViewScene = new Scene(gameView);
        changeScene(gameViewScene);
        currentController = new GameController(gameView, gameViewScene, board, settings, this, soundManager);
    }

    public void newGame(Settings settings, SoundManager soundManager) {
        Board board = new Board(settings.getSizeX(), settings.getSizeY(), settings.getFruitCount(),
                settings.getPlayerCount());
        changeToGame(settings, board, soundManager);
    }

    public void changeToPauseMenu(int width, int height, Settings settings, Board board, SoundManager soundManager) {
        PauseScreenView pauseScreenView = new PauseScreenView(width, height);
        Scene scene = new Scene(pauseScreenView);
        changeScene(scene);
        currentController = new PauseScreenController(pauseScreenView, board, settings, this, soundManager);

    }

    public void changeToSettings(int width, int height, Settings settings, SoundManager soundManager) {
        SettingsView settingsView = new SettingsView(width, height, settings);
        Scene settingsScene = new Scene(settingsView);
        changeScene(settingsScene);
        currentController = new SettingsController(settingsView, settingsScene, settings, this, soundManager);
    }
}
