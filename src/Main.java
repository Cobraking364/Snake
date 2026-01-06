package src;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    private int canvasWidth = 600;
    private int canvasHeight = 600;
    private int tile_size;
    private GraphicsContext gc;
    private Board board;
    int sizeX;
    int sizeY;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        List<String> parameters = getParameters().getRaw();
        // sizeX = Integer.parseInt(parameters.get(0))
        sizeX = 25;
        // sizeY = Integer.parseInt(parameters.get(1));
        sizeY = 25;
        sizeX = Math.clamp(sizeX, 5, 100);
        sizeY = Math.clamp(sizeY, 5, 100);

        board = new Board(sizeX, sizeY);
        Canvas canvas = new Canvas();
        tile_size = sizeX > sizeY ? canvasWidth / sizeX : canvasHeight / sizeY;
        canvas.setWidth(tile_size * sizeX);
        canvas.setHeight(tile_size * sizeY);

        gc = canvas.getGraphicsContext2D();
        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        // Selve vinduets indstillinger
        // stage.setResizable(true);

        // stage.setFullScreen(true);
        // stage.setFullScreenExitHint("press esc to exit");
        // stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("esc"));

        stage.setTitle("Snake");
        stage.show();

        Controller controller = new Controller(this, scene, board);
        update();
    }

    public void update() {
        drawBackground(sizeX, sizeY);
        drawFruit(board.getFruit().getPosition());
        drawSnake(board.getSnake().getBody());
    }

    public void drawBackground(int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                gc.setFill((i + j) % 2 == 0 ? Color.GREEN : Color.LIMEGREEN);
                gc.fillRect(i * tile_size, j * tile_size, tile_size, tile_size);
            }
        }
    }

    public void drawSnake(LinkedList<Position> snake) {

        Map<Position, Integer> layers = new HashMap<>();

        for (Position position : snake) {
                layers.put(position, layers.getOrDefault(position, 0) + 1);

        }

        for (Position position : snake) {
            gc.setFill(layers.get(position) == 1 ? Color.BLUE : Color.LIGHTBLUE);
            gc.fillRect(position.getX() * tile_size, position.getY() * tile_size, tile_size, tile_size);
        }
    }

    public void drawFruit(Position fruitPosition) {
        gc.setFill(Color.RED);
        gc.fillRect(fruitPosition.getX() * tile_size, fruitPosition.getY() * tile_size, tile_size, tile_size);
    }
}
