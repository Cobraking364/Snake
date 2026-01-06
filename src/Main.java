package src;

import java.util.LinkedList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int TILE_SIZE = 25;
    private GraphicsContext gc;
    private Board board;
    int sizeX = 24;
    int sizeY = 24;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        List<String> parameters = getParameters().getRaw();
        // sizeX = Integer.parseInt(parameters.get(0));
        sizeX = 24;
        // sizeY = Integer.parseInt(parameters.get(1));
        sizeY = 24;
        board = new Board(sizeX, sizeY);
        Canvas canvas = new Canvas();
        canvas.setWidth(600);
        canvas.setHeight(600);

        gc = canvas.getGraphicsContext2D();
        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        // Selve vinduets indstillinger
        stage.setResizable(true);

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

                gc.fillRect(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    public void drawSnake(LinkedList<Position> snake) {
        gc.setFill(Color.BLUE);
        for (Position position : snake) {
            gc.fillRect(position.getX() * TILE_SIZE, position.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
    }

    public void drawFruit(Position fruitPosition) {
        gc.setFill(Color.RED);
        gc.fillRect(fruitPosition.getX() * TILE_SIZE, fruitPosition.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
}
