package src;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class SettingsController extends Controller{
    private SettingsView view;
    private Scene scene;
    
    public SettingsController(SettingsView view, Scene scene, Settings settings, SceneManager SceneManager){
        super(settings, SceneManager);
        this.view = view;
        this.scene = scene;
        
        view.getBackButton().setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                
                MainMenuView mainMenuView = new MainMenuView((int) view.getWidth(), (int) view.getHeight());
                Scene mainMenuScene = new Scene(mainMenuView);
                MainMenuController mainMenuController = new MainMenuController(mainMenuView , mainMenuScene, getSettings(), getSceneManager());
                getSceneManager().changeScene(mainMenuScene);
            }
        });

        view.getSoundButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            }

        });

        view.getWidthSlider().valueProperty().addListener((ObservableValue,oldValue,newValue) -> {
            view.getLabelX().setText(String.valueOf(newValue.intValue()));
            getSettings().setSizeX(newValue.intValue());
        });


    
        view.getHeightSlider().valueProperty().addListener((ObservableValue,oldValue,newValue) -> {
            view.getLabelY().setText(String.valueOf(newValue.intValue()));
            getSettings().setSizeY(newValue.intValue());
        });
    }   

}
