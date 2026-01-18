package src;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private Stage stage;

    private GameController currentController;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    private void changeScene(Scene scene) {
        stage.setScene(scene);
    }

        public void changeToGame(int windowWidth, int windowHeight, int gameWidth, int gameHeight) {
        GameView gameView = new GameView(windowWidth, windowHeight, gameWidth, gameHeight);
        Board board = new Board(gameWidth, gameHeight);
        Scene gameViewScene = new Scene(gameView);
        changeScene(gameViewScene);
        currentController = new GameController(gameView, gameViewScene, board, this);
    }
}
