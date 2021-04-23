package game_objects;

public class BoatPosition {
    private boolean vertical;
    public Position position;

    public BoatPosition(boolean vertical, Position position) {
        this.position = position;
        this.vertical = vertical;
    }

    public boolean isVertical() {
        return vertical;
    }
}
