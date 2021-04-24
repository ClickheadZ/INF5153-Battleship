package tools;

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
     * @return true if format is valid, false otherwise
     * @throws IOException
     */
    public static boolean chooseFileType() {
        fileType = getInput();

        if(fileType.equals("txt") || fileType.equals("xml") || fileType.equals("json")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Reads user input from command line.
     * @return what user entered as a string
     * @throws IOException
     */
    public static String getInput() {
        String input = "";

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            input = reader.readLine();
        }
        catch(IOException e) {
            MessageBank.addMessageLog(MessageBank.ERROR_IO);
        }

        System.out.print("\n");

        return input;
    }

    // TODO: Methods to write to different file types
}
