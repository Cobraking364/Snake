package src;

import javafx.scene.media.AudioClip;

public class SoundManager {
    private static AudioClip eat = new AudioClip(SoundManager.class.getResource(Sounds.EAT.getSound()).toExternalForm());
    private static AudioClip jump = new AudioClip(SoundManager.class.getResource(Sounds.JUMP.getSound()).toExternalForm());
    private static AudioClip collision = new AudioClip(SoundManager.class.getResource(Sounds.COLLISION.getSound()).toExternalForm());

    public static void playSound(Sounds audioFile) {
        eat.play();
    }

}
