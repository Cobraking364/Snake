package src.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import src.models.Board;
import src.models.Settings;
import src.models.Sounds;
import src.views.PauseScreenView;

public class PauseScreenController extends Controller{
    private PauseScreenView view;
    
    public PauseScreenController(PauseScreenView view, Board board, Settings settings, SceneManager sceneManager, SoundManager soundManager){
        super(settings, sceneManager, soundManager);
        this.view = view;
        view.getResumeButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                soundManager.playSound(Sounds.CLICK, getSettings().getSoundVolume());
                getSceneManager().changeToGame(getSettings(), board, getSoundManager());
            }
        });

        view.getRestarButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                soundManager.playSound(Sounds.CLICK, getSettings().getSoundVolume());
                getSceneManager().newGame(getSettings(), getSoundManager());
            }
        });

        view.getQuitButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                soundManager.playSound(Sounds.CLICK, getSettings().getSoundVolume());
                Platform.exit();
            }
        });

        view.getMainMenuButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getSceneManager().changeToMainMenu((int) view.getWidth(), (int) view.getHeight(), getSettings(), getSoundManager());
            }
        });

    }
}
