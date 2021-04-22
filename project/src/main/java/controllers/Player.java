package controllers;

import game_objects.Boat;
import game_objects.Grid;

/**
 * Defines actions that a player can take on their turn, in both stages of the game.
 */
public class Player {
    private Grid playerGrid;
    public Boat[] boats;

    public Player() {
        this.playerGrid = new Grid();
        this.boats = new Boat[5];

        boats[0] = new Boat(5, "Carrier");
        boats[1] = new Boat(4, "Cruiser");
        boats[2] = new Boat(3, "Destroyer");
        boats[3] = new Boat(3, "Destroyer");
        boats[4] = new Boat(2, "Bomber");
    }

    /**
     * Attempts to place a boat in the player's grid at specified position.
     * If a boat is vertical, the rest of the body will be below specified position.
     * If it is horizontal, the body will be towards the right.
     * @return              true if boat successfully placed, false otherwise
     * @param   boatId      the index of the boat in boats[]
     * @param   placement   format : "v a5"
     */
    public boolean placeBoat(int boatId, String placement) {
        String[] splitPlacement = placement.split(" ");
        boolean vertical = false;

        // Input validation
        // TODO : Pass on all this input validation to IoHandler to improve clarity?
        if(splitPlacement[0].charAt(0) == 'v') {
            vertical = true;
        } else if (splitPlacement[0].charAt(0) == 'h') {
            vertical = false;
        } else {
            System.out.println("- FORMAT ERROR -");
            return false;
        }

        String position = splitPlacement[1];
        if(position.charAt(0) < 'a' || position.charAt(0) > 'j') {
            System.out.println("- Column letter must be between 'a' and 'j' -");
            return false;
        }

        String colString = "" + position.charAt(0);

        String rowString = "" + position.charAt(1);
        if(position.length() > 2) rowString += position.charAt(2);
        int row;
        try {
            row = Integer.parseInt(rowString);
            if(row < 1 || row > 10) {
                System.out.println("- Row number must be between 1 and 10 -");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("- FORMAT ERROR -");
            return false;
        }

        int size = boats[boatId].getSize();

        return playerGrid.placeBoatTiles(size, boatId, vertical, colString, rowString);
    }
}
