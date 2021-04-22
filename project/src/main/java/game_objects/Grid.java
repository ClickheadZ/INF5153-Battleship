package game_objects;

import game_objects.tiles.Tile;

import java.util.HashMap;

/**
 * Defines the properties and behaviour of a player grid.
 */
public class Grid {
    private Tile[][] grid;
    private HashMap<String,Integer> rows = new HashMap<String, Integer>();
    private HashMap<String,Integer> columns = new HashMap<String, Integer>();

    public Grid() {
        this.grid = new Tile[10][10];

        // Setting hashmaps to facilitate conversion from user input to grid position
        for(int i=0; i<10; ++i) {
            String key = i+1 + "";
            rows.put(key, i);
        }
        // Convert chars a-j to decimals 0-9
        for(int i=0; i<10; ++i) {
            String key = (char) (97 + i) + "";
            columns.put(key, i);
        }
    }

    public boolean placeBoatTiles(int size, int boatId, boolean vertical, String col, String row) {
        // TODO : place tiles for one boat

        return true;
    }

    public boolean placeTile(Tile tile) {
        // TODO : place a tile
        return true;
    }
}
