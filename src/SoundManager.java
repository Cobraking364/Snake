package src;

import javafx.scene.media.AudioClip;

public class SoundManager {

    public static void playSound(SoundManagerHelper audioFile) {
        AudioClip sound = new AudioClip(SoundManager.class.getResource(audioFile.getSound()).toExternalForm());
        sound.play();
    }

}
