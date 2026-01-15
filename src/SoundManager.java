package src;

import javafx.scene.media.AudioClip;

public class SoundManager {
    private static AudioClip bootup = new AudioClip(
            SoundManager.class.getResource(Sounds.BOOTUP.getSound()).toExternalForm());
    private static AudioClip click = new AudioClip(
            SoundManager.class.getResource(Sounds.CLICK.getSound()).toExternalForm());
    private static AudioClip collision = new AudioClip(
            SoundManager.class.getResource(Sounds.COLLISION.getSound()).toExternalForm());
    private static AudioClip eat = new AudioClip(
            SoundManager.class.getResource(Sounds.EAT.getSound()).toExternalForm());
    private static AudioClip jump = new AudioClip(
            SoundManager.class.getResource(Sounds.JUMP.getSound()).toExternalForm());
    private static AudioClip start = new AudioClip(
            SoundManager.class.getResource(Sounds.START.getSound()).toExternalForm());
    private static AudioClip powerup = new AudioClip(
            SoundManager.class.getResource(Sounds.POWERUP.getSound()).toExternalForm());
            
    public static void playSound(Sounds audioFile, int volume) {
        switch (audioFile) {
            case BOOTUP -> bootup.play(volume);
            case CLICK -> click.play(volume);
            case COLLISION -> collision.play(volume);
            case EAT -> eat.play(volume);
            case JUMP -> jump.play(volume);
            case START -> start.play(volume);
            case POWERUP -> powerup.play(volume);
            default -> {
            }
        }
    }
}
