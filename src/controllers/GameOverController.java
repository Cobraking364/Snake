package src.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import src.controllers.Controller;
import src.models.Settings;
import src.models.Sounds;
import src.views.GameOverView;

public class GameOverController extends Controller {
    private GameOverView view;

    public GameOverController(GameOverView view, Settings settings, SceneManager sceneManager, SoundManager soundManager) {
        super(settings, sceneManager, soundManager);
        this.view = view;
        view.getRestartButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getSceneManager().newGame(getSettings(), getSoundManager());
                soundManager.playSound(Sounds.START, getSettings().getSoundVolume());
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
