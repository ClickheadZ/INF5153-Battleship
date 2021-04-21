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

        // Repeatedly ask user for file type until they pick a valid one
        while(true) {
            System.out.print("Please select the type of file where logs will be stored " +
                    "(txt, xml or json) : ");

            if(IoHandler.chooseFileType()) {
                break;
            } else {
                System.out.println("Format ERROR.");
            }
        }

        // Repeatedly ask user for difficulty level until they pick a valid one
        int difficulty = 0;

        while(true) {
            System.out.print("Select a difficulty level (e = easy, m = medium, h = hard) : ");

            difficulty = IoHandler.chooseDifficulty();
            if(difficulty != 0) {
                break;
            } else {
                System.out.println("Format ERROR.");
            }
        }


    }
}
