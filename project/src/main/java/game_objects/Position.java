package game_objects;

public class Position {
    private int col;
    private int row;

    public Position() {
    }

    public Position(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }
}
