package controllers;

import game_objects.Boat;
import game_objects.Grid;
import game_objects.BoatPosition;
import game_objects.Position;
import game_objects.tiles.BoatTile;
import game_objects.tiles.Tile;
import tools.InputParser;
import tools.MessageBank;

import java.util.ArrayList;

/**
 * Defines actions that a player can take on their turn, in both stages of the game.
 */
public class Player {
    private Grid playerGrid;
    private Grid trackingGrid;
    public int boatsLeft;
    public Boat[] boats;
    public boolean skipTurn;
    public final boolean isUser;

    public Player(boolean isUser) {
        this.isUser = isUser;

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

    /**
     *
     * @param opponent
     * @param input
     * @return  true if a boat was sunk
     */
    public void launchAttack(Player opponent, String input) {
        Position position = InputParser.parsePosition(input);
        Tile tileHit = opponent.receiveAttack(position);

        // TODO : fix all attacks missing bug
        // NOTE : only the symbol update on playerGrid is working
        trackingGrid.setTileHit(tileHit.isBoat(), position);

        if(tileHit.isMine()) {
            skipTurn = true;
        }
    }

    /**
     *
     * @param position
     * @return the char of the tile that was hit
     */
    public Tile receiveAttack(Position position) {
        Tile tileHit = playerGrid.attackTile(position);

        if(tileHit.isBoat()) {
            MessageBank.addMessageLog(MessageBank.buildHitBoatMsg(!isUser));

            int boatId = ((BoatTile) tileHit).getBoatId();
            Boat targetBoat = boats[boatId];
            targetBoat.attack();

            if(targetBoat.getSize() < 1) {
                boatsLeft--;
                MessageBank.addMessageLog(MessageBank.buildSunkMsg(!isUser, targetBoat.getName()));
            }
        } else if(tileHit.isMine()) {
            MessageBank.addMessageLog(MessageBank.buildHitMineMsg(!isUser));
        } else {
            MessageBank.addMessageLog(MessageBank.buildMissMsg(!isUser));
        }

        return tileHit;
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

    public void printPlayerGrids() {
        String line = "";

        ArrayList<String> playerGridList = playerGrid.getGridLines();
        ArrayList<String> trackingGridList = trackingGrid.getGridLines();

        int index = 0;
        for(String gridLine : playerGridList) {
            line = gridLine + "     " + trackingGridList.get(index);
            System.out.println(line);
            ++index;
        }

        String footnote = "               Your grid                             Visible enemy grid\n";
        System.out.println(footnote);
    }
}
