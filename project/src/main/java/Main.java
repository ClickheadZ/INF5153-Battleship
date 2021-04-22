import controllers.*;
import tools.IoHandler;

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

        IoHandler.printOpening();
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

            difficulty = IoHandler.chooseDifficulty();
            if (difficulty != 0) {
                break;
            } else {
                System.out.println("- FORMAT ERROR -");
            }
        }

        // Start the Placement Phase of the game
        Player humanPlayer = new Player();
        Player aiPlayer = new Player();

        System.out.print("Do you want your boats to be placed randomly? (y / n) : ");
        boolean randomlyPlace = IoHandler.getInput().equals("y");

        if(!randomlyPlace) {
            System.out.println("Instructions : To place a boat, enter v or h depending on if you want the boat to " +
                    "be placed vertically or horizontally, followed by a space and then the letter representing the " +
                    "column, immediately followed by the number representing the row.\n " +
                    "i.e : 'v a1' or 'h j10'.\n\n" +
                    "If the boat is placed vertically, it will continue below the specified position on the grid. " +
                    "If it is placed horizontally, it will continue towards the right of the chosen position.");
        }
        PlacementManager.placeBoats(humanPlayer, randomlyPlace);
        PlacementManager.placeBoats(aiPlayer, true);
    }
}
