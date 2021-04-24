import controllers.*;
import tools.IoHandler;
import tools.MessageBank;

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
    public static void main(String[] args) {

        System.out.println(MessageBank.OPENING_SCREEN);

        // Repeatedly ask user for file type until they pick a valid one
        while(true) {
            System.out.print("Please select the type of file where logs will be stored " +
                    "(txt, xml or json) : ");

            if(IoHandler.chooseFileType()) {
                break;
            } else {
                MessageBank.addMessageLog(MessageBank.ERROR_OPTION);
                MessageBank.printMessageLog();
            }
        }

        GameController gameController = new GameController();

        gameController.askDifficulty();

        gameController.boatPlacementPhase();

        gameController.minePlacementPhase();

        gameController.battlePhase();

        // TODO : add restart game functionality after battle phase
        System.out.println(MessageBank.ENDING_MSG + "*INSERT FILENAME HERE*");
    }
}
