package src;

import java.util.Objects;

class Position{
    private int x;
    private int y;

    Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Position other = (Position) obj;
        return (getX() == other.getX() && getY() == other.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getY(), getY());
    }
}