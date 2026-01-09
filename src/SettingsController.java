package src;



import javax.swing.event.ChangeListener;

import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class SettingsController {
    private SettingsView view;
    private Scene scene;
    
    public SettingsController(SettingsView view, Scene scene, int gameWidth, int gameHeight,Settings setting, SceneManager sceneManager){
        this.view = view;
        this.scene = scene;
        
        view.getBackButton().setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                
                MainMenuView mainMenuView = new MainMenuView((int) view.getWidth(), (int) view.getHeight());
                Scene mainMenuScene = new Scene(mainMenuView);
                MainMenuController mainMenuController = new MainMenuController(mainMenuView , mainMenuScene, gameWidth, gameHeight, setting, sceneManager);
                sceneManager.changeScene(mainMenuScene);
            }
        });

        view.getSoundButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            }

        });

        view.getWidthSlider().valueProperty().addListener((ObservableValue,oldValue,newValue) -> {
            view.getLabelX().setText(String.valueOf(newValue.intValue()));
            setting.setsizeX(newValue.intValue());
        });


    
        view.getHeightSlider().valueProperty().addListener((ObservableValue,oldValue,newValue) -> {
            view.getLabelY().setText(String.valueOf(newValue.intValue()));
            setting.setsizeY(newValue.intValue());
        });
    }   

}
