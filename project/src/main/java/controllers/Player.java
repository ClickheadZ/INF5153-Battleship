package controllers;

import game_objects.Boat;
import game_objects.Grid;
import game_objects.BoatPosition;
import game_objects.Position;
import tools.InputParser;

/**
 * Defines actions that a player can take on their turn, in both stages of the game.
 */
public class Player {
    private Grid playerGrid;
    private Grid trackingGrid;
    private int boatsLeft;
    public Boat[] boats;
    public boolean skipTurn;

    public Player() {
        this.playerGrid = new Grid();
        this.trackingGrid = new Grid();
        this.boatsLeft = 5;
        this.boats = new Boat[5];
        initializeBoats();
        skipTurn = false;
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

    public void launchAttack(Player opponent, String input) {
        Position position = InputParser.parsePosition(input);
        boolean attackHit = opponent.receiveAttack(position);
        trackingGrid.setTileHit(attackHit, position);
    }

    public boolean receiveAttack(Position position) {
        return playerGrid.attackTile(position);
    }

    public void initializeBoats() {
        boats[0] = new Boat(5, "Carrier");
        boats[1] = new Boat(4, "Battleship");
        boats[2] = new Boat(3, "Destroyer");
        boats[3] = new Boat(3, "Destroyer");
        boats[4] = new Boat(2, "Patrol Boat");
    }

    public void printPlayerGrid() {
        // May have more behaviour later
        playerGrid.printGrid();
    }
}
