package game_objects.tiles;

/**
 * Defines an object that is placeable on a player grid
 */
public class Tile {
    public char symbol;
    public boolean hit = false;

    public Tile() {}

    public Tile(char symbol) {
        this.symbol = symbol;
    }

    public boolean isBoat() {
        return this.symbol == 'B';
    }
    public boolean isMine() { return this.symbol == 'M'; }
}
