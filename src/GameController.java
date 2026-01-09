package src;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameController extends Controller {

    private GameView view;
    private Scene scene;
    private Board board;
    private Settings settings;
    private InputBuffer inputBuffer;
    private GameLoop gameLoop;

    GameController(GameView view, Scene scene, Board board, Settings settings, SceneManager sceneManager) {
        super(settings, sceneManager);
        this.view = view;
        this.scene = scene;
        this.board = board;
        inputBuffer = new InputBuffer(3);
        updateView();

        ChangeListener<Number> windowSizeListener = (observable, oldValue, newValue) -> Platform
                .runLater(this::updateView);

        scene.getWindow().widthProperty().addListener(windowSizeListener);
        scene.getWindow().heightProperty().addListener(windowSizeListener);
        gameLoop = new GameLoop(settings.getSnakeSpeed()) {
            @Override
            public void update(double deltaTime) {
                if (inputBuffer.hasInput()) {
                    handleInput(inputBuffer.getNext());
                }

                board.update();
                draw();

                if (board.getIsGameOver()) {
                    gameLoop.stop();
                    GameOverView gameOverView = new GameOverView();
                    GameOverController gameOverController = new GameOverController(gameOverView, getSettings(), getSceneManager());

                    view.getChildren().add(gameOverView);
                }
            }
        };

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case KeyCode.LEFT:
                    case KeyCode.RIGHT:
                    case KeyCode.UP:
                    case KeyCode.DOWN:
                    case KeyCode.SPACE:
                        inputBuffer.addInput(event.getCode());
                }

            }

        });
        gameLoop.start();
    }

    private void handleInput(KeyCode input) {
        switch (input) {
            case KeyCode.LEFT:
                board.getSnake().updateDirection(Direction.LEFT);
                break;
            case KeyCode.RIGHT:
                board.getSnake().updateDirection(Direction.RIGHT);
                break;
            case KeyCode.UP:
                board.getSnake().updateDirection(Direction.UP);
                break;
            case KeyCode.DOWN:
                board.getSnake().updateDirection(Direction.DOWN);
                break;
            case KeyCode.SPACE:
                board.getSnake().jump();

        }
    }

    private void updateView() {
        view.updateTileSize();
        draw();
    }

    private void draw() {
        view.drawBackground(board.getSizeX(), board.getSizeY());
        view.drawFruit(board.getFruit().getPosition(), board.getSizeX(), board.getSizeY());
        view.drawSnake(board.getSnake().getBody(), board.getSizeX(), board.getSizeY());
    }
}