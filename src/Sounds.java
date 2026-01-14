package src;

enum Sounds {
    EAT("pickupFruit.wav"),
    COLLISION("collision.wav"),
    JUMP("jump.wav"),
    START("start.wav"),
    CLICK("click.wav"),
    BOOTUP("bootup.wav");
    

    private final String soundPath;

    Sounds(final String newSoundPath) {
        soundPath = newSoundPath;
    }

    public String getSound() {
        return "/resources/sounds/" + soundPath;
    }
}
