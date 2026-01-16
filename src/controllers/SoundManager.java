package src.controllers;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import src.models.Sounds; 

public class SoundManager {
    private MediaPlayer bootup = loadSound(Sounds.BOOTUP);
    private MediaPlayer click = loadSound(Sounds.CLICK);
    private MediaPlayer collision = loadSound(Sounds.COLLISION);
    private MediaPlayer eat = loadSound(Sounds.EAT);
    private MediaPlayer jump = loadSound(Sounds.JUMP);
    private MediaPlayer start = loadSound(Sounds.START);
    private MediaPlayer powerup = loadSound(Sounds.POWERUP);

    public SoundManager() {
        bootup = loadSound(Sounds.BOOTUP);
        click = loadSound(Sounds.CLICK);
        collision = loadSound(Sounds.COLLISION);
        eat = loadSound(Sounds.EAT);
        jump = loadSound(Sounds.JUMP);
        start = loadSound(Sounds.START);
        powerup = loadSound(Sounds.POWERUP);
    }

    private MediaPlayer loadSound(Sounds sound) {
        Media media = new Media(
                SoundManager.class.getResource(sound.getSound()).toExternalForm());
        return new MediaPlayer(media);
    }

    public void playSound(Sounds audioFile, int intVolume) {
        double volume = Math.clamp((intVolume / 100.0), 0, 1);
        MediaPlayer mediaPlayer = switch (audioFile) {
            case BOOTUP -> bootup;
            case CLICK -> click;
            case COLLISION -> collision;
            case EAT -> eat;
            case JUMP -> jump;
            case START -> start;
            default -> new MediaPlayer(null);
        };
        mediaPlayer.stop();
        mediaPlayer.setVolume(volume);
        mediaPlayer.play();
    }
}
