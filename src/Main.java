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
        // sizeX = Integer.parseInt(parameters.get(0));
        sizeX = 24;
        // sizeY = Integer.parseInt(parameters.get(1));
        sizeY = 24;

        board = new Board(sizeX, sizeY);
        Canvas canvas = new Canvas();
        canvas.setWidth(canvasWidth);
        canvas.setHeight(canvasHeight);
        tile_size=canvasWidth/sizeX;


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

                gc.fillRect(i * tile_size, j * tile_size, tile_size, tile_size);
            }
        }
    }

    public void drawSnake(LinkedList<Position> snake) {
        gc.setFill(Color.BLUE);
        for (Position position : snake) {
            gc.fillRect(position.getX() * tile_size, position.getY() * tile_size, tile_size, tile_size);
        }
    }

    public void drawFruit(Position fruitPosition) {
        gc.setFill(Color.RED);
        gc.fillRect(fruitPosition.getX() * tile_size, fruitPosition.getY() * tile_size, tile_size, tile_size);
    }
}
