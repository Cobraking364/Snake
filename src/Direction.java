package src;

enum Direction{
    UP(0),
    LEFT(1),
    DOWN(2),
    RIGHT(3);

    private final int value;

    Direction(final int newValue){
        value = newValue;
    }

    public int getValue() {return value;};
}
