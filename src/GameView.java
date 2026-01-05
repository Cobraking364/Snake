package src;
import java.util.ArrayList;
import java.util.LinkedList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameView extends Application {

    private GraphicsContext gc;
    public static void main(String[] args) {
        launch(args);
    }

    public void start (Stage stage){
        
        Canvas canvas = new Canvas(750, 750);
        gc = canvas.getGraphicsContext2D();
        
        
        StackPane root = new StackPane();
        
        
        root.getChildren().add(canvas);
        //holder.setStyle("-fx-background-color: limegreen");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        //Selve vinduets indstillinger
        stage.setResizable(true);
        
        //stage.setFullScreen(true);
        //stage.setFullScreenExitHint("press esc to exit");
        //stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("esc"));

        stage.setTitle("Snake");
        stage.show();

    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void backgroundDraw(int x, int y, Canvas canvas){
        double gridSize = canvas.getWidth() / x;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                gc.setFill((i+j) % 2 == 0 ? Color.GREEN : Color.LIMEGREEN);

                gc.fillRect(i*gridSize, j*gridSize, gridSize, gridSize);
            }
        }
    }

    public void drawSnake(LinkedList<Position> Snake, int x, int y, Canvas canvas){
        double gridSize = canvas.getWidth() / x;
        gc.setFill(Color.BLUE);
        for (Position position : Snake) {
            
            gc.fillRect(position.getX()*gridSize, position.getY()*gridSize, gridSize, gridSize); 
        }
    }

    public void drawFruit(int x,int fruitx, int fruity, Canvas canvas){
        double gridSize = canvas.getWidth() / x;
        gc.setFill(Color.RED);
        gc.fillRect(fruitx*gridSize, fruity*gridSize, gridSize, gridSize); 
        //gc.fillRect(fruitx*gridSize, fruity*gridSize, canvas.getWidth()/x, canvas.getHeight()/y);
    }

    
}
