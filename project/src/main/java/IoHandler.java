import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Handles all I/O operations, including user input and writing to files.
 */
public class IoHandler {
    private static String fileType;

    /**
     * Asks user for file type on the command line, has to be txt or xml or json.
     *
     * @return true if format is valid, false otherwise
     * @throws IOException
     */
    public static boolean chooseFileType() throws IOException {
        fileType = getInput();

        if(fileType.equals("txt") || fileType.equals("xml") || fileType.equals("json")) {
            return true;
        } else {
            return false;
        }
    }

    public static int chooseDifficulty() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String difficulty = reader.readLine();
        int difficultyScore;

        switch (difficulty) {
            case "e":
                difficultyScore = 1;
                break;
            case "m":
                difficultyScore = 2;
                break;
            case "h":
                difficultyScore = 3;
                break;
            default:
                difficultyScore = 0;
        }

        return difficultyScore;
    }

    public static String getInput() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        return reader.readLine();
    }

    // TODO: Methods to write to different file types
}
