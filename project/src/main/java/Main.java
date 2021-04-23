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

        GameController gameController = new GameController();

        gameController.askDifficulty();

        gameController.boatPlacementPhase();

        gameController.minePlacementPhase();
    }
}
