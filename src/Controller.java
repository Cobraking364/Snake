package src;

public class Controller {

    private Settings settings;
    private SceneManager sceneManager;


    Controller(Settings settings, SceneManager sceneManager) {
        this.settings = settings;
        this.sceneManager = sceneManager;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Settings getSettings() {
        return settings;
    }
    
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
    
    public SceneManager getSceneManager() {
        return sceneManager;
    }
}  
