package src;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.io.InputStream;
import javafx.scene.media.AudioClip;

public class SoundManager {
    public static void main(String[] args) {
        AudioClip pickFruit = new AudioClip(SoundManager.class.getResource("pickupFruit.wav")
                .toExternalForm());
        pickFruit.play();
        try {
    Thread.sleep(3000);
} catch(InterruptedException e) {
    System.out.println("got interrupted!");
}
    }
}
