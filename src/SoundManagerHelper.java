package src;

enum SoundManagerHelper {
    EAT("pickFruit.wav"),
    COLLISION("collision.wav"),
    JUMP("jump.wav"),
    START("start.wav");

    private final String soundPath;

    SoundManagerHelper(final String newSoundPath) {
        soundPath = newSoundPath;
    }

    public String getSound() {
        return "/resources/sounds/" + soundPath;
    }
}
