package src;

import javafx.scene.paint.Color;

public enum SnakeColor {
    BLUE(Color.BLUE),
    VIOLET(Color.VIOLET),
    ORANGE(Color.ORANGE),
    YELLOW(Color.YELLOW);
    
    private final Color value;

    SnakeColor(final Color newValue){
        value = newValue;
    }

    public Color getValue() {return value;};
}
