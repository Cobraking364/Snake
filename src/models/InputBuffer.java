package src.models;

import java.util.LinkedList;
import javafx.scene.input.KeyCode;

public class InputBuffer {

    private LinkedList<KeyCode> inputs;
    private int size;

    public InputBuffer(int size) {
        inputs = new LinkedList<>();
        this.size = size;
    }

    public KeyCode getNext() {
        return inputs.removeFirst();
    }

    public boolean hasInput() {
        return !inputs.isEmpty();
    }

    public void addInput(KeyCode input) {
        if (inputs.size() < size) {
            inputs.add(input);
        }
    }
}
