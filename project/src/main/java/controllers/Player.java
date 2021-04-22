package controllers;

import game_objects.Boat;
import game_objects.Grid;

/**
 * Defines actions that a player can take on their turn, in both stages of the game.
 */
public class Player {
    private Grid playerGrid;
    private Boat[] boats;

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
        if(splitPlacement[0].charAt(0) == 'v') {
            vertical = true;
        }
        String position = splitPlacement[1];
        int amount = boats[boatId].getSize();
        // TODO : finish this method by calling placeTiles or smth on Grid
        return false;
    }
}
