package src;

import javafx.scene.media.AudioClip;

public class SoundManager {
    private AudioClip bootup = loadSound(Sounds.BOOTUP);
    private AudioClip click = loadSound(Sounds.CLICK);
    private AudioClip collision = loadSound(Sounds.COLLISION);
    private AudioClip eat = loadSound(Sounds.EAT);
    private AudioClip jump = loadSound(Sounds.JUMP);
    private AudioClip start = loadSound(Sounds.START);
    private AudioClip powerup = loadSound(Sounds.POWERUP);

    public SoundManager() {
        bootup = loadSound(Sounds.BOOTUP);
        click = loadSound(Sounds.CLICK);
        collision = loadSound(Sounds.COLLISION);
        eat = loadSound(Sounds.EAT);
        jump = loadSound(Sounds.JUMP);
        start = loadSound(Sounds.START);
        powerup = loadSound(Sounds.POWERUP);
    }

    private AudioClip loadSound(Sounds sound) {
        AudioClip clip = new AudioClip(
                SoundManager.class.getResource(sound.getSound()).toExternalForm());
        return clip;
    }

    public void playSound(Sounds audioFile, int intVolume) {
        System.out.println(audioFile);
        double volume = Math.clamp((intVolume / 100.0), 0, 1);
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
