package game_objects;

import game_objects.tiles.*;
import tools.MessageBank;

/**
 * Defines the properties and behaviour of a player grid.
 */
public class Grid {

    private Tile[][] grid;

    public Grid() {
        this.grid = new Tile[10][10];
        initializeGrid();
    }

    public boolean placeBoatTiles(int size, int boatId, BoatPosition boatPosition) {
        int col = boatPosition.position.getCol();
        int row = boatPosition.position.getRow();
        boolean vertical = boatPosition.isVertical();

        // Checking for out of bounds
        if(vertical && row + size > 10) {
            MessageBank.addMessageLog(MessageBank.ERROR_BOUNDS);
            return false;
        }
        if(!vertical && col + size > 10) {
            MessageBank.addMessageLog(MessageBank.ERROR_BOUNDS);
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
        Tile mine = new Tile('M');
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

        if(!grid[col][row].isMine() && !grid[col][row].isBoat()) {
            canPlace = true;
        } else {
            MessageBank.addMessageLog(MessageBank.ERROR_COLLISION);
        }

        if(canPlace && tile.isBoat()) {
            canPlace = !neighboursBoat( (BoatTile) tile, col, row);
            if(!canPlace) MessageBank.addMessageLog(MessageBank.ERROR_BOATS_TOUCH);
        }

        return canPlace;
    }

    public boolean neighboursBoat(BoatTile tile, int col, int row) {
        if(col > 0) {
            Tile above = grid[col-1][row];
            if (above.isBoat()) {
                if (!tile.hasSameId( (BoatTile) above) ) return true;
            }
        }
        if(col < 9) {
            Tile below = grid[col+1][row];
            if (below.isBoat()) {
                if (!tile.hasSameId( (BoatTile) below) ) return true;
            }
        }
        if(row > 0) {
            Tile left = grid[col][row-1];
            if (left.isBoat()) {
                if (!tile.hasSameId( (BoatTile) left) ) return true;
            }
        }
        if(row < 9) {
            Tile right = grid[col][row+1];
            if (right.isBoat()) {
                if (!tile.hasSameId( (BoatTile) right) ) return true;
            }
        }

        return false;
    }

    public Tile attackTile(Position position) {
        Tile targetTile = grid[position.getCol()][position.getRow()];

        setTileHit((targetTile.isBoat()), position);

        return targetTile;
    }

    public void setTileHit(boolean hit, Position position) {
        grid[position.getCol()][position.getRow()].symbol = hit ? 'x' : 'o';
    }

    public void initializeGrid() {
        for(int i=0; i<10; ++i) {
            for(int j=0; j<10; ++j) {
                Tile water = new Tile('.');
                grid[i][j] = water;
            }
        }
    }

    public void printGrid() {
        System.out.print("\n");
        System.out.println("      a  b  c  d  e  f  g  h  i  j ");
        System.out.print("    __|__|__|__|__|__|__|__|__|__|__");

        for(int i=0; i<10; ++i) {
            String linePrint = "\n " + (i+1) + " [  ";
            if(i == 9) linePrint = "\n" + (i+1) + " [  ";

            for(int j=0; j<10; ++j) {
                char gridSymbol;
                gridSymbol = grid[j][i].symbol;
                linePrint += gridSymbol + "  ";
            }
            linePrint += "|";
            System.out.print(linePrint);
        }

        System.out.println("\n    --------------------------------\n");
    }
}
