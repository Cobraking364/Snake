package src;

import javafx.scene.input.KeyCode;

public class KeyControls {
    private KeyCode upButton;
    private KeyCode leftButton;
    private KeyCode downButton;
    private KeyCode rightButton;
    private KeyCode jumpButton;

    public KeyControls(KeyCode upButton, KeyCode leftButton, KeyCode downButton, KeyCode rightButton,
            KeyCode jumpButton) {
        this.upButton = upButton;
        this.leftButton = leftButton;
        this.downButton = downButton;
        this.rightButton = rightButton;
        this.jumpButton = jumpButton;
    }

    public KeyCode getUpButton() {
        return upButton;
    }

    public KeyCode getLeftButton() {
        return leftButton;
    }

    public KeyCode getDownButton() {
        return downButton;
    }

    public KeyCode getRightButton() {
        return rightButton;
    }

    public KeyCode getJumpButton() {
        return jumpButton;
    }

    public boolean containsKey(KeyCode code) {
        return code == getUpButton()
                || code == getLeftButton()
                || code == getRightButton()
                || code == getDownButton()
                || code == getJumpButton();
    }

}
