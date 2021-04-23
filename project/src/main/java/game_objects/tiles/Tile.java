package game_objects.tiles;

/**
 * Defines an object that is placeable on a player grid
 */
public abstract class Tile {
    public char symbol;
    public String name;
    public boolean hit = false;

    // TODO: Define behaviour when hit

    public boolean isBoat() {
        if(this.symbol == 'B') {
            return true;
        } else {
            return false;
        }
    }

    public boolean isMine() {
        if(this.symbol == 'M') {
            return true;
        } else {
            return false;
        }
    }
}
