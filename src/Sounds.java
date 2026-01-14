package src;

enum Sounds {
    EAT("pickFruit.wav"),
    COLLISION("collision.wav"),
    JUMP("jump.wav"),
    START("start.wav");

    private final String soundPath;

    Sounds(final String newSoundPath) {
        soundPath = newSoundPath;
    }

    public String getSound() {
        return "/resources/sounds/" + soundPath;
    }
}
