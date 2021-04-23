package controllers;

import game_objects.Boat;
import game_objects.Grid;
import game_objects.BoatPosition;
import game_objects.Position;
import tools.InputParser;

import java.io.IOException;

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
     * @param   input   format : "v a5"
     */
    public boolean placeBoat(int boatId, String input) {
        if(!InputParser.validBoatInput(input)) return false;

        BoatPosition boatPosition = InputParser.parseBoatPosition(input);

        int size = boats[boatId].getSize();

        return playerGrid.placeBoatTiles(size, boatId, boatPosition);
    }

    public boolean placeMine(String input) {
        if(!InputParser.validPosition(input)) return false;

        Position position = InputParser.parsePosition(input);

        return playerGrid.placeMineTile(position);
    }

    public void printPlayerGrid() {
        // May have more behaviour later
        playerGrid.printGrid();
    }
}
