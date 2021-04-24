package game_objects.tiles;

/**
 * Defines an object that is placeable on a player grid
 */
public abstract class Tile {
    public char symbol;
    public boolean hit = false;

    public boolean isBoat() { return this.symbol == 'B'; }

    public boolean isMine() { return this.symbol == 'm'; }

    public boolean isWater() { return this.symbol == '.'; }

}
