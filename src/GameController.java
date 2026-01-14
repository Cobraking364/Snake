package src;

import java.util.LinkedList;

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
    private InputBuffer[] inputBuffers;
    private GameLoop gameLoop;

    GameController(GameView view, Scene scene, Board board, Settings settings, SceneManager sceneManager) {
        super(settings, sceneManager);
        this.view = view;
        this.scene = scene;
        this.board = board;
        inputBuffers = new InputBuffer[settings.getPlayerCount()];
        for (int i = 0; i < inputBuffers.length; i++) {
            inputBuffers[i] = new InputBuffer(3);
        }
        updateView();

        ChangeListener<Number> windowSizeListener = (observable, oldValue, newValue) -> Platform
                .runLater(this::updateView);

        scene.getWindow().widthProperty().addListener(windowSizeListener);
        scene.getWindow().heightProperty().addListener(windowSizeListener);
        gameLoop = new GameLoop(settings.getSnakeSpeed()) {
            @Override
            public void update(double deltaTime) {
                for (InputBuffer inputBuffer : inputBuffers) {
                    if (inputBuffer.hasInput()) {
                        handleInput(inputBuffer.getNext());
                    }
                    
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
                        inputBuffers[0].addInput(event.getCode());
                        break;
                    case KeyCode.A:
                    case KeyCode.D:
                    case KeyCode.W:
                    case KeyCode.S:
                    case KeyCode.Z:
                        inputBuffers[1].addInput(event.getCode());
                }

            }

        });
        gameLoop.start();
    }

    private void handleInput(KeyCode input) {
        switch (input) {
            case KeyCode.LEFT:
                board.getSnakes().get(0).updateDirection(Direction.LEFT);
                break;
            case KeyCode.RIGHT:
                board.getSnakes().get(0).updateDirection(Direction.RIGHT);
                break;
            case KeyCode.UP:
                board.getSnakes().get(0).updateDirection(Direction.UP);
                break;
            case KeyCode.DOWN:
                board.getSnakes().get(0).updateDirection(Direction.DOWN);
                break;
            case KeyCode.SPACE:
                board.getSnakes().get(0).jump();
                break;
            case KeyCode.A:
                board.getSnakes().get(1).updateDirection(Direction.LEFT);
                break;
            case KeyCode.D:
                board.getSnakes().get(1).updateDirection(Direction.RIGHT);
                break;
            case KeyCode.W:
                board.getSnakes().get(1).updateDirection(Direction.UP);
                break;
            case KeyCode.S:
                board.getSnakes().get(1).updateDirection(Direction.DOWN);
                break;
            case KeyCode.Z:
                board.getSnakes().get(1).jump();
                break;
        }
    }

    private void updateView() {
        view.updateTileSize();
        draw();
    }

    private void draw() {
        view.drawBackground(board.getSizeX(), board.getSizeY());
        for (Fruit fruit : board.getFruits()) {
            view.drawFruit(fruit.getPosition(), board.getSizeX(), board.getSizeY());
        }
        LinkedList<Position>[] snakeBodies = new LinkedList[getSettings().getPlayerCount()];
        for (int i = 0; i < getSettings().getPlayerCount(); i++) {
            snakeBodies[i] = board.getSnakes().get(i).getBody();
        }
        view.drawSnakes(snakeBodies);
    }
}