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

    /**
     *
     * @return true if player wins, false if AI wins
     */
    public boolean battlePhase() {
        System.out.println(MessageBank.INSTRUCTIONS_BATTLE_PHASE);

        // while(true), loop exits right after a player turn
        // player1 performs an attack, then check winconditions
        // player2 performs an attack, then check winconditions
        while(true) {
            //humanplayer turn
            if(!humanPlayer.skipTurn) {
                String attackInput;
                boolean inputIsValid = true;
                do {
                    if(!inputIsValid) MessageBank.printErrorMsg();

                    System.out.print("Select which tile you want to attack : ");
                    attackInput = IoHandler.getInput();
                    inputIsValid = InputParser.validPosition(attackInput);
                } while (!inputIsValid);

                humanPlayer.launchAttack(aiPlayer, attackInput);
                // I need the attack to do the following things :
                //
                // [x] Set the enemy tile to the right symbol (grid.attackTile)
                // [ ] Set tracking grid tile to the right symbol ()
                // [ ] Update player.boats[id] if one of its tiles was hit ()
                // [ ] Update player.boatsLeft if the boat has size 0 ()
                // [ ] If mine was hit, switch a boolean to skip next turn ()
            }


            // TODO: check win condition now
            // TODO : think about the simplest way to check for win/loss now

            //aiPlayer turn
            if(!aiPlayer.skipTurn) {

            }
            // check win condition again
        }
    }
}
