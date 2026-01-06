package src;
import java.util.LinkedList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameView extends Canvas{

    private static final int TILE_SIZE = 50;
    private GraphicsContext gc;


    public GameView(){
        super();
        gc = getGraphicsContext2D();
    }


    public void drawBackground(int width, int height){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                gc.setFill((i+j) % 2 == 0 ? Color.GREEN : Color.LIMEGREEN);

                gc.fillRect(i*TILE_SIZE, j*TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    public void drawSnake(LinkedList<Position> snake){
        gc.setFill(Color.BLUE);
        for (Position position : snake) {
            gc.fillRect(position.getX()*TILE_SIZE, position.getY()*TILE_SIZE, TILE_SIZE, TILE_SIZE); 
        }
    }

    public void drawFruit(Position fruitPosition){
        gc.setFill(Color.RED);
        gc.fillRect(fruitPosition.getX()*TILE_SIZE, fruitPosition.getY()*TILE_SIZE, TILE_SIZE, TILE_SIZE); 
        //gc.fillRect(fruitx*TILE_SIZE, fruity*TILE_SIZE, canvas.getWidth()/x, canvas.getHeight()/y);
    }

    
}
