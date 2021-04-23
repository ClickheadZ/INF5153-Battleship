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
        int tileCol = columns.get(col);
        int tileRow = rows.get(row);

        // Checking for out of bounds
        if(vertical && tileRow + size > 10) {
            //System.out.println("ERROR : Boat out of bounds.");
            return false;
        }
        if(!vertical && tileCol + size > 10) {
            //System.out.println("ERROR : Boat out of bounds.");
            return false;
        }

        Tile boatTile = new BoatTile(boatId);

        // Check if all tiles can be placed
        for(int i=0; i<size; ++i) {
            if(!canPlaceTile(boatTile, tileCol, tileRow)) return false;

            if(vertical) {
                tileRow += 1;
            } else {
                tileCol += 1;
            }
        }

        tileCol = columns.get(col);
        tileRow = rows.get(row);

        // Place all tiles
        for(int i=0; i<size; ++i) {
            grid[tileCol][tileRow] = boatTile;

            if(vertical) {
                tileRow += 1;
            } else {
                tileCol += 1;
            }
        }

        return true;
    }

    public boolean placeMineTile(String col, String row) {
        Tile mine = new MineTile();
        int tileCol = columns.get(col);
        int tileRow = rows.get(row);

        if(!canPlaceTile(mine, tileCol, tileRow)) {
            return false;
        } else {
            grid[tileCol][tileRow] = mine;
            return true;
        }
    }

    public boolean canPlaceTile(Tile tile, int col, int row) {
        boolean canPlace = false;

        if(grid[col][row] == null) {
            canPlace = true;
        } else {
            System.out.println("ERROR : A tile is already occupied.");
        }

        if(canPlace && tile.symbol == 'B') {
            canPlace = !neighboursBoat( (BoatTile) tile, col, row);
            if(!canPlace) System.out.print("");//System.out.println("ERROR : Boat cannot touch another boat.");
        }

        return canPlace;
    }

    public boolean neighboursBoat(BoatTile tile, int col, int row) {

        // TODO : refactor this disgusting mess
        if(col > 0) {
            Tile above = grid[col-1][row];
            if (above != null && above.isBoat()) {
                if (!tile.hasSameId( (BoatTile) above) ) return true;
            }
        }

        if(col < 9) {
            Tile below = grid[col+1][row];
            if (below != null && below.isBoat()) {
                if (!tile.hasSameId( (BoatTile) below) ) return true;
            }
        }

        if(row > 0) {
            Tile left = grid[col][row-1];
            if (left != null && left.isBoat()) {
                if (!tile.hasSameId( (BoatTile) left) ) return true;
            }
        }

        if(row < 9) {
            Tile right = grid[col][row+1];
            if (right != null && right.isBoat()) {
                if (!tile.hasSameId( (BoatTile) right) ) return true;
            }
        }

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
                if(grid[j][i] == null) {
                    gridSymbol = '.';
                } else {
                    gridSymbol = grid[j][i].symbol;
                }
                linePrint += gridSymbol + " ";
            }
            linePrint += "|";
            System.out.print(linePrint);
        }

        System.out.println("\n   -----------------------\n");
    }
}
