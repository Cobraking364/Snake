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

    public static void playSound(Sounds audioFile) {
        switch (audioFile) {
            case BOOTUP:
                bootup.play();
                break;
            case CLICK:
                click.play();
                break;
            case COLLISION:
                collision.play();
                break;
            case EAT:
                eat.play(0.75);
                break;
            case JUMP:
                jump.play();
                break;
            case START:
                start.play();
                break;
            default:
                break;
        }
    }
}
