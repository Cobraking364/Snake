package src;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private Stage stage;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    public void changeScene(Scene scene) {
        stage.setScene(scene);
    }
}
