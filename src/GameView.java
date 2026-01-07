package src;

import java.util.LinkedList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class GameView extends StackPane{
    private Canvas canvas;
    private int canvasWidth;
    private int canvasHeight;
    private GraphicsContext gc;

    public GameView(int canvasWidth, int canvasHeight) {
        this.canvasHeight = canvasHeight;
        this.canvasHeight = canvasHeight;
        canvas = new Canvas();
        gc = canvas.getGraphicsContext2D();
        canvas.setWidth(canvasWidth);
        canvas.setHeight(canvasHeight);
        getChildren().add(canvas);
    }


    public void drawBackground(int width, int height) {
        int tileSize = getTileSize(width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                gc.setFill((i + j) % 2 == 0 ? Color.GREEN : Color.LIMEGREEN);
                gc.fillRect(i * tileSize, j * tileSize, tileSize, tileSize);
            }
        }
    }

    public void drawSnake(LinkedList<Position> snake, int width, int height) {
        int tileSize = getTileSize(width, height);
        gc.setFill(Color.BLUE);

        for (Position position : snake) {
            gc.fillRect(position.getX() * tileSize, position.getY() * tileSize, tileSize, tileSize);
        }
    }

    public void drawFruit(Position fruitPosition, int width, int height) {
        int tileSize = getTileSize(width, height);

        gc.setFill(Color.RED);
        gc.fillRect(fruitPosition.getX() * tileSize, fruitPosition.getY() * tileSize, tileSize, tileSize);
    }

    public int getTileSize(int sizeX, int sizeY) {
        return sizeX > sizeY ? canvasWidth / sizeX : canvasHeight / sizeY;
    } 
}
