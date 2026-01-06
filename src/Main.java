package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        int sizeX = 24;
        int sizeY = 24;

        Board board = new Board(sizeX, sizeY);
        GameView view = new GameView();
        view.setWidth(600);
        view.setHeight(600);


        StackPane root = new StackPane();

        root.getChildren().add(view);
        // holder.setStyle("-fx-background-color: limegreen");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        // Selve vinduets indstillinger
        stage.setResizable(true);

        // stage.setFullScreenExitHint("press esc to exit");
        // stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("esc"));

        stage.setTitle("Snake");
        stage.show();
        
        view.drawBackground(sizeX, sizeY);
        view.drawFruit(board.getFruit().getPosition());
        view.drawSnake(board.getSnake().getBody());


    }
}
