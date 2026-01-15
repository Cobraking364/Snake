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
        // Updates view on screen resize
        ChangeListener<Number> windowSizeListener = (observable, oldValue, newValue) -> Platform
                .runLater(this::updateView);

        scene.getWindow().widthProperty().addListener(windowSizeListener);
        scene.getWindow().heightProperty().addListener(windowSizeListener);

        // Inits game clock
        gameLoop = new GameLoop(settings.getSnakeSpeed()) {
            @Override
            public void update(double deltaTime) {
                for (InputBuffer inputBuffer : inputBuffers) {
                    if (inputBuffer.hasInput()) {
                        handleInput(inputBuffer.getNext());
                    }

                }

                board.update(deltaTime);
                draw();

                if (board.getIsGameOver()) {
                    gameLoop.stop();
                    GameOverView gameOverView = new GameOverView();
                    GameOverController gameOverController = new GameOverController(gameOverView, getSettings(),
                            getSceneManager());

                    view.getChildren().add(gameOverView);
                }
            }
        };

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                for (int i = 0; i < getSettings().getPlayerCount(); i++) {
                    if (getSettings().getKeyControls()[i].containsKey(code)) {
                        inputBuffers[i].addInput(code);
                    }

                }

            }

        });
        gameLoop.start();
    }

    private void handleInput(KeyCode input) {

            for (int i = 0; i < getSettings().getPlayerCount(); i++) {
                KeyControls keyControls = getSettings().getKeyControls()[i];
                if (input == keyControls.getUpButton()) {board.getSnakes().get(i).updateDirection(Direction.UP);}
                else if (input == keyControls.getLeftButton()) {board.getSnakes().get(i).updateDirection(Direction.LEFT);}
                else if (input == keyControls.getDownButton()) {board.getSnakes().get(i).updateDirection(Direction.DOWN);}
                else if (input == keyControls.getRightButton()) {board.getSnakes().get(i).updateDirection(Direction.RIGHT);}
                else if (input == keyControls.getJumpButton()) {board.getSnakes().get(i).jump();}
            }

    }

    private void updateView() {
        view.updateTileSize();
        draw();
    }

    private void draw() {
        view.drawBackground(board.getSizeX(), board.getSizeY());
        for (Fruit fruit : board.getFruits()) {
            view.drawFruit(fruit.getPosition());
        }
        for (SnakePowerup powerup : board.getPowerups()) {
            view.drawPowerUp(powerup.getPosition());
        }
        LinkedList<Position>[] snakeBodies = new LinkedList[getSettings().getPlayerCount()];
        for (int i = 0; i < getSettings().getPlayerCount(); i++) {
            snakeBodies[i] = board.getSnakes().get(i).getBody();
        }
        view.drawSnakes(snakeBodies);
    }
}