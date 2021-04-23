import controllers.*;
import tools.InputParser;
import tools.IoHandler;
import tools.MessageBank;

import java.io.IOException;

/**
 * ||============================================================||
 * ||   x    ______   M   _   _   _   x       _     _            ||
 * ||   B    | ___ \     | | | | | |         | |   (_)     BBBx  ||
 * ||   B    | |_/ / __ _| |_| |_| | ___  ___| |__  _ _ __       ||
 * ||   B    | ___ \/ _` | __| __| |/ _ \/ __| '_ \| | '_ \      ||
 * ||        | |_/ / (_| | |_| |_| |  __/\__ \ | | | | |_) |     ||
 * ||        \____/ \__,_|\__|\__|_|\___||___/_| |_|_| .__/      ||
 * ||   x x            x                             | |     x   ||
 * ||       x                M         x             |_|         ||
 * ||============================================================||
 *
 * Programme écrit dans le câdre du cours INF5153 - Génie Logiciel: Conception
 *
 * @author Alex Moreno
 * @codeEtudiant MORA28069309
 */

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println(MessageBank.OPENING_SCREEN);
        // Repeatedly ask user for file type until they pick a valid one
        while(true) {
            System.out.print("Please select the type of file where logs will be stored " +
                    "(txt, xml or json) : ");

            if(IoHandler.chooseFileType()) {
                break;
            } else {
                System.out.println("- FORMAT ERROR -");
            }
        }

        // Repeatedly ask user for difficulty level until they pick a valid one
        int difficulty = 0;

        while(true) {
            System.out.print("Select a difficulty level (e = easy, m = medium, h = hard) : ");

            difficulty = InputParser.chooseDifficulty(IoHandler.getInput());
            if (difficulty != 0) {
                break;
            } else {
                System.out.println("- FORMAT ERROR -");
            }
        }

        Player humanPlayer = new Player();
        Player aiPlayer = new Player();

        // Placing boats for both players
        System.out.print("Do you want your boats to be placed randomly? (y / n) : ");
        boolean randomlyPlace = IoHandler.getInput().equals("y");

        humanPlayer.printPlayerGrid();
        if(!randomlyPlace) {
            System.out.println(MessageBank.INSTRUCTIONS_BOAT_PLACEMENT);
        }

        PlacementManager.placeBoats(humanPlayer, randomlyPlace);
        PlacementManager.placeBoats(aiPlayer, true);

        humanPlayer.printPlayerGrid();
        System.out.println("Your final grid.\nIt will be shown again after every move.");

        // TODO : Replace what main is doing with GameController, with methods like BoatPlacementPhase();

        // Placing mines for both players
        System.out.print("Do you want your mines to be placed randomly? (y / n) : ");
        randomlyPlace = IoHandler.getInput().equals("y");

        humanPlayer.printPlayerGrid();
        if(!randomlyPlace) {
            System.out.println(MessageBank.INSTRUCTIONS_MINE_PLACEMENT);
        }

        PlacementManager.placeMines(humanPlayer, randomlyPlace);
        PlacementManager.placeMines(aiPlayer, true);
        humanPlayer.printPlayerGrid();
    }
}
