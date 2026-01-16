package src;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameView extends StackPane {
    private Canvas canvas;
    private GraphicsContext gc;
    private int tileSize;
    private int gameWidth;
    private int gameHeight;
    private Label scoreLabel;
    private final Color[] SNAKE_COLORS = {SnakeColor.BLUE.getValue(), SnakeColor.VIOLET.getValue(), SnakeColor.ORANGE.getValue(), SnakeColor.YELLOW.getValue()};
    private final Color OVERLAP_COLOR = Color.LIGHTYELLOW;
    private final Color POWERUP_COLOR = Color.PURPLE;

    public GameView(int gameWidth, int gameHeight) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        canvas = new Canvas();
        gc = canvas.getGraphicsContext2D();
        updateTileSize();
        getStylesheets().add(getClass().getResource("/resources/menu.css").toExternalForm());
        Rectangle blackBackground = new Rectangle();
        blackBackground.setFill(Color.BLACK);
        blackBackground.heightProperty().bind(heightProperty());
        blackBackground.widthProperty().bind(widthProperty());
        scoreLabel = new Label("");
        scoreLabel.setId("score-label");
        VBox scoreVBox =new VBox();
        scoreVBox.setAlignment(Pos.TOP_RIGHT);
        scoreVBox.getChildren().add(scoreLabel);
        VBox.setMargin(scoreLabel, new Insets(10,10,0,0));
        getChildren().add(blackBackground);
        getChildren().add(canvas);
        getChildren().add(scoreVBox);
    }

    public void drawBackground(int width, int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                gc.setFill((i + j) % 2 == 0 ? Color.GREEN.brighter() : Color.LIMEGREEN);
                gc.fillRect(i * tileSize, j * tileSize, tileSize, tileSize);
            }
        }
    }

    public void drawSnakes(LinkedList<Position>[] snakeBodies) {
        Map<Position, Integer> layers = new HashMap<>();
        for (int i = 0; i < snakeBodies.length; i++) {
            gc.setFill(SNAKE_COLORS[i]);
            // Bodies
            snakeBodies[i].forEach(position -> {
                layers.put(position, layers.getOrDefault(position, 0) + 1);
                gc.fillRect(position.getX() * tileSize, position.getY() * tileSize, tileSize, tileSize);
            });
            // Heads
            gc.setFill(SNAKE_COLORS[i].darker());
            Position head = snakeBodies[i].get(snakeBodies[i].size() - 1);
            gc.fillRect(head.getX() * tileSize, head.getY() * tileSize, tileSize, tileSize);
        }
        // Overlaps
        gc.setFill(OVERLAP_COLOR);
        layers.forEach((position, count) -> {
            if (count >= 2) {
                gc.fillRect(position.getX() * tileSize, position.getY() * tileSize, tileSize, tileSize);
            }
        });
    }

    public void drawFruit(Position fruitPosition) {
        gc.setFill(Color.RED);
        gc.fillRect(fruitPosition.getX() * tileSize, fruitPosition.getY() * tileSize, tileSize, tileSize);
    }
    public void drawPowerUp(Position powerUpPosition) {
        gc.setFill(POWERUP_COLOR);
        gc.fillRect(powerUpPosition.getX() * tileSize, powerUpPosition.getY() * tileSize, tileSize, tileSize);
    }

    public void updateTileSize() {
        tileSize = Math.min(widthProperty().intValue() / gameWidth, heightProperty().intValue() / gameHeight);
        canvas.setWidth(tileSize * gameWidth);
        canvas.setHeight(tileSize * gameHeight);
    }

    public void updateScore(int score){
        scoreLabel.setText(""+score);
    }
}
