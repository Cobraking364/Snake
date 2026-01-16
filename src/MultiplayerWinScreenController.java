package src;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MultiplayerWinScreenController extends Controller{
    private MultiplayerWinScreenView view;
    
    public MultiplayerWinScreenController(MultiplayerWinScreenView view, Settings settings, SceneManager sceneManager, SoundManager soundManager){
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