package src;

import javafx.scene.media.AudioClip;

public class SoundManager {
    private static double volume = (Settings.getSoundVolume())/100.0; //placeholder
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
            
    public static void playSound(Sounds audioFile) {
        switch (audioFile) {
            case BOOTUP -> bootup.play((Settings.getSoundVolume())/100.0);
            case CLICK -> click.play((Settings.getSoundVolume())/100.0);
            case COLLISION -> collision.play((Settings.getSoundVolume())/100.0);
            case EAT -> eat.play((Settings.getSoundVolume())/100.0);
            case JUMP -> jump.play((Settings.getSoundVolume())/100.0);
            case START -> start.play((Settings.getSoundVolume())/100.0);
            case POWERUP -> powerup.play((Settings.getSoundVolume())/100.0);
            default -> {
            }
        }
    }
}
