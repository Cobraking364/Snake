package src;

import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Main extends Application {
    private final int CANVAS_WIDTH = 600;
    private final int CANVAS_HEIGHT= 600;
    private int tile_size;
    private GraphicsContext gc;
    private Board board;
    int sizeX = 5;
    int sizeY = 5;

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Exactly two parameters required");
        }
        try {
            int x = Integer.parseInt(args[0]);
            int y = Integer.parseInt(args[1]);
        } catch (NumberFormatException  e) {
            throw new IllegalArgumentException("Parameters must be integers.");
        }
        
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        List<String> parameters = getParameters().getRaw();

        sizeX = Integer.parseInt(parameters.get(0));
        sizeY = Integer.parseInt(parameters.get(1));

        sizeX = Math.clamp(sizeX, 5, 100);
        sizeY = Math.clamp(sizeY, 5, 100);

        board = new Board(sizeX, sizeY);
        SceneManager sceneManager = new SceneManager(stage);
        GameView gameView = new GameView(CANVAS_WIDTH, CANVAS_HEIGHT, sizeX, sizeY);
        Scene gameViewScene = new Scene(gameView);
        GameController gameController = new GameController(gameView, gameViewScene, board, sceneManager);
        
        sceneManager.changeScene(gameViewScene);
        stage.setResizable(false);
        stage.setTitle("Snake");
        stage.show();
    }
}
