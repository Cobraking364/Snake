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
        Canvas canvas = new Canvas();
        tile_size = sizeX > sizeY ? canvasWidth / sizeX : canvasHeight / sizeY;
        canvas.setWidth(tile_size * sizeX);
        canvas.setHeight(tile_size * sizeY);

        gc = canvas.getGraphicsContext2D();
        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
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
