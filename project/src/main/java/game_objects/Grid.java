package game_objects;

import java.util.HashMap;

/**
 * Defines the properties and behaviour of a player grid.
 */
public class Grid {
    private Tile[][] grid;
    private HashMap<String,Integer> rows = new HashMap();
    private HashMap<String,Integer> columns = new HashMap();

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

    public boolean placeTiles(int amount, boolean vertical) {
        return true;
    }
}
