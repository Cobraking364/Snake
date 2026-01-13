package src;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.io.InputStream;
import javafx.scene.media.AudioClip;

public class SoundManager {
    public static void playPickupFruit() {
        AudioClip pickFruit = new AudioClip(SoundManager.class.getResource("/resources/sounds/pickupFruit.wav")
                .toExternalForm());
        pickFruit.play();
    }

    public static void playCollision() {
        AudioClip collision = new AudioClip(SoundManager.class.getResource("/resources/sounds/collision.wav") //should maybe be played on gameoverscreen? i dunno if sound dies if view changes
                .toExternalForm());
        collision.play();
    }

    public static void playJump() {
        AudioClip jump = new AudioClip(SoundManager.class.getResource("/resources/sounds/jump.wav")
                .toExternalForm());
        jump.play();
    }

    public static void playStart() {
        AudioClip start = new AudioClip(SoundManager.class.getResource("/resources/sounds/start.wav")
                .toExternalForm());
        start.play();
    }

}
