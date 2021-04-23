package controllers;

import tools.InputParser;
import tools.IoHandler;
import tools.MessageBank;

public class GameController {

    private Player humanPlayer;
    private Player aiPlayer;

    public GameController() {}

    /**
     * Repeatedly asks user for difficulty level until they pick a valid one
     */
    public void askDifficulty() {
        int difficulty = 0;

        while(true) {
            System.out.print("Select a difficulty level (e = easy, m = medium, h = hard) : ");

            difficulty = InputParser.chooseDifficulty(IoHandler.getInput());
            if (difficulty != 0) {
                break;
            } else {
                System.out.println(MessageBank.ERROR_OPTION);
            }
        }
    }

    /**
     * Handles the boat placement phase of the game.
     */
    public void boatPlacementPhase() {
        humanPlayer = new Player();
        aiPlayer = new Player();

        System.out.print("Do you want your boats to be placed randomly? (y / n) : ");
        boolean randomlyPlace = IoHandler.getInput().equals("y");

        humanPlayer.printPlayerGrid();
        if(!randomlyPlace) {
            System.out.println(MessageBank.INSTRUCTIONS_BOAT_PLACEMENT);
        }

        PlacementManager.placeBoats(humanPlayer, randomlyPlace);
        PlacementManager.placeBoats(aiPlayer, true);

        humanPlayer.printPlayerGrid();
    }

    /**
     * Handles the mine placement phase of the game.
     */
    public void minePlacementPhase() {
        System.out.print("Do you want your mines to be placed randomly? (y / n) : ");
        boolean randomlyPlace = IoHandler.getInput().equals("y");

        humanPlayer.printPlayerGrid();
        if(!randomlyPlace) {
            System.out.println(MessageBank.INSTRUCTIONS_MINE_PLACEMENT);
        }

        PlacementManager.placeMines(humanPlayer, randomlyPlace);
        PlacementManager.placeMines(aiPlayer, true);

        humanPlayer.printPlayerGrid();
        System.out.println("Your final grid.\nIt will be shown again after every move.");
    }
}
