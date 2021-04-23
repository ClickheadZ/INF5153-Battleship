package game_objects;

import game_objects.tiles.BoatTile;
import game_objects.tiles.MineTile;
import game_objects.tiles.Tile;

/**
 * Defines the properties and behaviour of a player grid.
 */
public class Grid {

    private Tile[][] grid;

    public Grid() {
        this.grid = new Tile[10][10];
    }

    public boolean placeBoatTiles(int size, int boatId, BoatPosition boatPosition) {
        int col = boatPosition.position.getCol();
        int row = boatPosition.position.getRow();
        boolean vertical = boatPosition.isVertical();

        // Checking for out of bounds
        if(vertical && row + size > 10) {
            //System.out.println("ERROR : Boat out of bounds.");
            return false;
        }
        if(!vertical && col + size > 10) {
            //System.out.println("ERROR : Boat out of bounds.");
            return false;
        }

        Tile boatTile = new BoatTile(boatId);

        // Check if all tiles can be placed
        for(int i=0; i<size; ++i) {
            if(vertical) {
                if(!canPlaceTile(boatTile, col, row + i)) return false;
            } else {
                if(!canPlaceTile(boatTile, col + i, row)) return false;
            }
        }

        // Place all tiles
        for(int i=0; i<size; ++i) {
            if (vertical) {
                grid[col][row + i] = boatTile;
            } else {
                grid[col + i][row] = boatTile;
            }
        }

        return true;
    }

    public boolean placeMineTile(Position position) {
        Tile mine = new MineTile();
        int col = position.getCol();
        int row = position.getRow();

        if(canPlaceTile(mine, col, row)) {
            grid[col][row] = mine;
            return true;
        } else {
            return false;
        }
    }

    public boolean canPlaceTile(Tile tile, int col, int row) {
        boolean canPlace = false;

        if(grid[col][row] == null) {
            canPlace = true;
        } else {
            //System.out.println("ERROR : A tile is already occupied.");
        }

        if(canPlace && tile.isBoat()) {
            canPlace = !neighboursBoat( (BoatTile) tile, col, row);
            if(!canPlace) System.out.print("");//System.out.println("ERROR : Boat cannot touch another boat.");
        }

        return canPlace;
    }

    public boolean neighboursBoat(BoatTile tile, int col, int row) {
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
