package src.controllers;

import java.util.LinkedList;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import src.models.Board;
import src.models.Direction;
import src.models.Fruit;
import src.models.InputBuffer;
import src.models.KeyControls;
import src.models.Position;
import src.models.Settings;
import src.models.SnakeColor;
import src.models.Sounds;
import src.models.powerups.SnakePowerup;
import src.views.GameOverView;
import src.views.GameView;
import src.views.MultiplayerWinScreenView;
import src.views.WinScreenView;

public class GameController extends Controller {

    private GameView view;
    private Board board;
    private InputBuffer[] inputBuffers;
    private GameLoop gameLoop;

    GameController(GameView view, Scene scene, Board board, Settings settings, SceneManager sceneManager,
            SoundManager soundManager) {
        super(settings, sceneManager, soundManager);
        this.view = view;
        this.board = board;
        inputBuffers = new InputBuffer[settings.getPlayerCount()];
        for (int i = 0; i < inputBuffers.length; i++) {
            inputBuffers[i] = new InputBuffer(3);
        }

        updateView();

        if (!board.getIsGameMultiplayer()) {
            view.updateScore(board.getScore());
        }

        // Binds screen resizing to updating
        ChangeListener<Number> windowSizeListener = (observable, oldValue, newValue) -> Platform
                .runLater(this::updateView);

        scene.getWindow().widthProperty().addListener(windowSizeListener);
        scene.getWindow().heightProperty().addListener(windowSizeListener);

        // Inits game clock
        gameLoop = new GameLoop(settings.getSnakeSpeed()) {
            @Override
            public void update(double deltaTime) {
                if (board.getIsgameWon() || board.getIsGameOver()) {
                    gameLoop.stop();
                    handleGameOver();
                    return;
                }

                for (InputBuffer inputBuffer : inputBuffers) {
                    if (inputBuffer.hasInput()) {
                        handleInput(inputBuffer.getNext());
                    }

                }
                if (board.getHasSnakeJumped()) {
                    soundManager.playSound(Sounds.JUMP, settings.getSoundVolume());
                }
                if (board.getHasCollided()) {
                    soundManager.playSound(Sounds.COLLISION, settings.getSoundVolume());
                }

                board.update(deltaTime);

                if (board.getHasEaten()) {
                    soundManager.playSound(Sounds.EAT, getSettings().getSoundVolume());
                    if (!board.getIsGameMultiplayer()) {
                        view.updateScore(board.getScore());
                    }
                }
                if (board.getHasPowerUped()) {
                    soundManager.playSound(Sounds.POWERUP, getSettings().getSoundVolume());
                }
                draw();

            }
        };

        scene.setOnKeyPressed((KeyEvent event) -> {
            KeyCode code = event.getCode();
            for (int i = 0; i < getSettings().getPlayerCount(); i++) {
                if (getSettings().getKeyControls()[i].containsKey(code)) {
                    inputBuffers[i].addInput(code);
                }
                
            }
            if (code == KeyCode.ESCAPE) {
                gameLoop.stop();
                getSceneManager().changeToPauseMenu((int) view.getWidth(), (int) view.getHeight(), getSettings(),
                        board, getSoundManager());
            }
        });
        gameLoop.start();
    }

    private void handleInput(KeyCode input) {
        for (int i = 0; i < getSettings().getPlayerCount(); i++) {
            KeyControls keyControls = getSettings().getKeyControls()[i];
            if (input == keyControls.getUpButton()) {
                board.getSnakes().get(i).updateDirection(Direction.UP);
            } else if (input == keyControls.getLeftButton()) {
                board.getSnakes().get(i).updateDirection(Direction.LEFT);
            } else if (input == keyControls.getDownButton()) {
                board.getSnakes().get(i).updateDirection(Direction.DOWN);
            } else if (input == keyControls.getRightButton()) {
                board.getSnakes().get(i).updateDirection(Direction.RIGHT);
            } else if (input == keyControls.getJumpButton()) {
                board.getSnakes().get(i).jump();
            }
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

    private void handleGameOver() {
        if (board.getIsGameOver()) {
            handleSingeplayerGameOver();
        } else if (board.getIsGameMultiplayer()) {
            handleMultiplayerGameOver();
        } else {
            handleSinglePlayerWin();
        }
    }

    private void handleSingeplayerGameOver() {
        HighscoreHandler highscoreHandler = new HighscoreHandler();
        highscoreHandler.checkHighscore(board.getSizeX(), board.getSizeY(), board.getScore());
        int highscore = highscoreHandler.getHighscore(board.getSizeX(), board.getSizeY());

        GameOverView gameOverView = new GameOverView(board.getScore(), highscore, getSettings());
        GameOverController gameOverController = new GameOverController(gameOverView, getSettings(),
                getSceneManager(), getSoundManager());
        view.getChildren().add(gameOverView);
    }

    private void handleMultiplayerGameOver() {
        String winText = switch (board.getWinnerIndex()) {
            case 0 -> "" + SnakeColor.BLUE;
            case 1 -> "" + SnakeColor.VIOLET;
            case 2 -> "" + SnakeColor.ORANGE;
            case 3 -> "" + SnakeColor.YELLOW;
            default -> "NO ONE";
        } + " WON";
        MultiplayerWinScreenView multiplayerWinScreenView = new MultiplayerWinScreenView(winText);
        MultiplayerWinScreenController multiplayerWinScreenController = new MultiplayerWinScreenController(
                multiplayerWinScreenView, getSettings(),
                getSceneManager(), getSoundManager());
        view.getChildren().add(multiplayerWinScreenView);
    }

    private void handleSinglePlayerWin() {
        HighscoreHandler highscoreHandler = new HighscoreHandler();
        highscoreHandler.checkHighscore(board.getSizeX(), board.getSizeY(), board.getScore());
        int highscore = highscoreHandler.getHighscore(board.getSizeX(), board.getSizeY());
        WinScreenView winScreenView = new WinScreenView(board.getScore(), highscore, getSettings());
        WinScreenController winScreenController = new WinScreenController(winScreenView, getSettings(),
                getSceneManager(), getSoundManager());
        view.getChildren().add(winScreenView);
    }
}
