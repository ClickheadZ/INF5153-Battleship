package controllers;

import controllers.ai.Ai;
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
                MessageBank.addMessageLog(MessageBank.ERROR_OPTION);
                MessageBank.printMessageLog();
            }
        }
    }

    /**
     * Handles the boat placement phase of the game.
     */
    public void boatPlacementPhase() {
        humanPlayer = new Player(true);
        aiPlayer = new Player(false);

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
        System.out.println("Your final grid.\nIt will be shown again after every move.\n");
    }

    /**
     *
     * @return true if player wins, false if AI wins
     */
    public boolean battlePhase() {
        System.out.println(MessageBank.INSTRUCTIONS_BATTLE_PHASE);

        while(true) {
            // Human player turn
            if(!humanPlayer.skipTurn) {
                String attackInput;
                boolean inputIsValid = true;
                do {
                    if(!inputIsValid) MessageBank.printMessageLog();

                    System.out.print("Select which tile you want to attack : ");
                    attackInput = IoHandler.getInput();
                    inputIsValid = InputParser.validPosition(attackInput);
                } while (!inputIsValid);

                humanPlayer.launchAttack(aiPlayer, attackInput);
                MessageBank.printMessageLog();
            } else {
                System.out.println("You skip your turn...");
                humanPlayer.skipTurn = false;
            }

            if(aiPlayer.boatsLeft == 0) return true;

            // AiPlayer turn
            if(!aiPlayer.skipTurn) {
                // TODO : AI selection needs to affect this part of the code

                aiPlayer.launchAttack(humanPlayer, Ai.selectRandomPosition());
                MessageBank.printMessageLog();
            } else {
                System.out.println("The enemy skips their turn...\n");
                aiPlayer.skipTurn = false;
            }

            humanPlayer.printPlayerGrids();
            System.out.println("Enemy boats left : " + aiPlayer.boatsLeft);

            if(humanPlayer.boatsLeft == 0) return false;
        }
    }
}
