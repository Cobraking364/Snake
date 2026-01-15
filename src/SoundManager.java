package src;

import javafx.scene.media.AudioClip;

public class SoundManager {
    private static AudioClip bootup = loadSound(Sounds.BOOTUP);
    private static AudioClip click = loadSound(Sounds.CLICK);
    private static AudioClip collision = loadSound(Sounds.COLLISION);
    private static AudioClip eat = loadSound(Sounds.EAT);
    private static AudioClip jump = loadSound(Sounds.JUMP);
    private static AudioClip start = loadSound(Sounds.START);
    private static AudioClip powerup = loadSound(Sounds.POWERUP);

    private static AudioClip loadSound(Sounds sound){
        AudioClip clip  = new AudioClip(
            SoundManager.class.getResource(sound.getSound()).toExternalForm());
            clip.play(0.0); 
            clip.stop();
            return clip;
    }

    public static void playSound(Sounds audioFile, int intVolume) {
        double volume = Math.clamp((intVolume/100.0),0,1);
        switch (audioFile) {
            case BOOTUP -> bootup.play(volume);
            case CLICK -> click.play(volume);
            case COLLISION -> collision.play(volume);
            case EAT -> eat.play(volume);
            case JUMP -> jump.play(volume);
            case START -> start.play(volume);
            default -> {
            }
        }
    }
}
