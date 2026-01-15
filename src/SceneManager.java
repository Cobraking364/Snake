package src;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private Stage stage;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    public void changeScene(Scene scene) {
        stage.setScene(scene);
    }

    public void newGame(Settings settings) {
        GameView gameView = new GameView(settings.getSizeX(), settings.getSizeY());
        Scene gameViewScene = new Scene(gameView);
        Board board = new Board(settings.getSizeX(), settings.getSizeY(), settings.getFruitCount(), settings.getPlayerCount());
        changeScene(gameViewScene);
        GameController gameController = new GameController(gameView, gameViewScene, board, settings, this);
    }
}
