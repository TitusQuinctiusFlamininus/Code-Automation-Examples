package engine;

/**
 * Created by michael.nyika on 21/08/15.
 */
public class Cell {
    private int X;
    private int Y;
    private boolean alive;

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
