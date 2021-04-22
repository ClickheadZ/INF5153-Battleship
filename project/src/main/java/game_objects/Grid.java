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
    // TODO : Give HashMaps to player? Might save a lot of trouble and make more sense.

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
            int tileCol = columns.get(col);
            int tileRow = rows.get(row);

            // Checking for out of bounds
            if(vertical && tileCol + size > 9) {
                System.out.println("- ERROR : Boat out of bounds -");
                return false;
            }
            if(!vertical && tileRow + size > 9) {
                System.out.println("- ERROR : Boat out of bounds -");
                return false;
            }

            if(vertical) {
                tileCol += i;
            } else {
                tileRow += i;
            }

            Tile boatTile = new BoatTile(boatId);

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
        boolean canPlace = false;

        if(grid[col][row] == null) {
            grid[col][row] = tile;
            canPlace = true;
        } else {
            System.out.println("- ERROR : Tile already occupied -");
        }

        if(canPlace && tile.symbol == 'B') {
            canPlace = !neighboursBoat(col, row);
        }

        return canPlace;
    }

    public boolean neighboursBoat(int col, int row) {
        if(col > 0 && grid[col-1][row] != null && grid[col-1][row].symbol == 'B') return true;
        if(col < 9 && grid[col+1][row] != null && grid[col+1][row].symbol == 'B') return true;
        if(row > 0 && grid[col][row-1] != null && grid[col][row-1].symbol == 'B') return true;
        if(row < 9 && grid[col][row+1] != null && grid[col][row+1].symbol == 'B') return true;

        // TODO : fix these checks because for some reason they block the program
        System.out.println("- ERROR : Boat cannot touch another boat -");
        return false;
    }

    public void printGrid() {
        System.out.print("\n");
        System.out.println("     a b c d e f g h i j");
        System.out.print("   _______________________");

        for(int i=0; i<10; ++i) {
            String linePrint = "\n " + (i+1) + " | ";
            if(i == 9) linePrint = "\n" + (i+1) + " | ";

            for(int j=0; j<10; ++j) {
                char gridSymbol;
                if(grid[i][j] == null) {
                    gridSymbol = '.';
                } else {
                    gridSymbol = grid[i][j].symbol;
                }
                linePrint += gridSymbol + " ";
            }
            linePrint += "|";
            System.out.print(linePrint);
        }

        System.out.println("\n   -----------------------\n");
    }
}
