package game_objects;

import game_objects.tiles.BoatTile;
import game_objects.tiles.MineTile;
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
        for(int i=0; i<size; ++i) {
            Tile boatTile = new BoatTile(boatId);

            int tileCol = columns.get(col);
            int tileRow = rows.get(row);

            if(vertical) {
                tileCol += i;
            } else {
                tileRow += i;
            }

            if(!placeTile(boatTile, tileCol, tileRow)) return false;
        }

        return true;
    }

    public boolean placeMineTile(String col, String row) {
        Tile mine = new MineTile();
        int tileCol = columns.get(col);
        int tileRow = rows.get(row);

        return placeTile(mine, tileCol, tileRow);
    }

    /**
     * Attempts to place a tile at specified position on the grid.
     * @return  false if there is already a tile there
     */
    public boolean placeTile(Tile tile, int col, int row) {
        if(grid[col][row] != null) {
            return false;
        } else {
            grid[col][row] = tile;
            return true;
        }
    }

    public void printGrid() {

    }
}
