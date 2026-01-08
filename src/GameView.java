package src;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameView extends StackPane {
    private int windowWidth;
    private Canvas canvas;
    private GraphicsContext gc;
    private int tileSize;

    public GameView(int windowWidth, int windowHeight, int gameWidth, int gameHeight) {
        tileSize = gameWidth > gameHeight ? windowWidth / gameWidth : windowHeight / gameHeight;
        ;
        canvas = new Canvas();
        gc = canvas.getGraphicsContext2D();
        canvas.setWidth(tileSize * gameWidth);
        canvas.setHeight(tileSize * gameHeight);
        Rectangle blackBackground = new Rectangle();
        blackBackground.setFill(Color.BLACK);
        blackBackground.setWidth(windowWidth);
        blackBackground.setHeight(windowHeight);
        getChildren().add(blackBackground);
        getChildren().add(canvas);

    }

    public void drawBackground(int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                gc.setFill((i + j) % 2 == 0 ? Color.GREEN : Color.LIMEGREEN);
                gc.fillRect(i * tileSize, j * tileSize, tileSize, tileSize);
            }
        }
    }

    public void drawSnake(LinkedList<Position> snake, int width, int height) {
        // Body
        Map<Position, Integer> layers = new HashMap<>();
        for (int i = 0; i < snake.size() - 1; i++) {
            Position position = snake.get(i);
            layers.put(position, layers.getOrDefault(position, 0) + 1);
        }
        
        gc.setFill(Color.BLUE);
        for (Position position : layers.keySet()) {
            gc.setFill(layers.get(position) == 1 ? Color.BLUE : Color.LIGHTBLUE);
            gc.fillRect(position.getX() * tileSize, position.getY() * tileSize, tileSize, tileSize);
        }

        // Head
        gc.setFill(Color.DARKBLUE);

        Position head = snake.get(snake.size() - 1);
        gc.fillRect(head.getX() * tileSize, head.getY() * tileSize, tileSize, tileSize);
    }

    public void drawFruit(Position fruitPosition, int width, int height) {
        gc.setFill(Color.RED);
        gc.fillRect(fruitPosition.getX() * tileSize, fruitPosition.getY() * tileSize, tileSize, tileSize);
    }
}
