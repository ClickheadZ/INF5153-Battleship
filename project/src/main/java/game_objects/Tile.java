package game_objects;

/**
 * Defines an object that is placeable on a player grid
 */
abstract class Tile {
    public char symbol;
    public String name;
    public boolean hit = false;

    // TODO: Define behaviour when hit
}
