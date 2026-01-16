package src.controllers;

import src.models.Settings;

public class Controller {

    private Settings settings;
    private SceneManager sceneManager;
    private SoundManager soundManager;


    Controller(Settings settings, SceneManager sceneManager, SoundManager soundManager) {
        this.settings = settings;
        this.sceneManager = sceneManager;
        this.soundManager = soundManager;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Settings getSettings() {
        return settings;
    }
    
    public SceneManager getSceneManager() {
        return sceneManager;
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }
}  
