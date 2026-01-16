package src.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import src.models.Sounds;
import src.views.WinScreenView;
import src.controllers.SoundManager;
import src.models.Settings;
import src.controllers.SceneManager;
import src.controllers.Controller;

public class WinScreenController extends Controller{
    private WinScreenView view;
    
    public WinScreenController(WinScreenView view, Settings settings, SceneManager sceneManager, SoundManager soundManager){
        super(settings, sceneManager, soundManager);
        this.view = view;
        view.getPlayAgainButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getSceneManager().newGame(getSettings(), getSoundManager());
                soundManager.playSound(Sounds.CLICK, getSettings().getSoundVolume());
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
                soundManager.playSound(Sounds.CLICK, getSettings().getSoundVolume());
                getSceneManager().changeToMainMenu((int) view.getWidth(), (int) view.getHeight(), getSettings(), getSoundManager());
            }
        });

    }
}
