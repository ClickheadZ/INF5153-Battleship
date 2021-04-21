package controllers;

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
    public static boolean chooseFileType() throws IOException {
        fileType = getInput();

        if(fileType.equals("txt") || fileType.equals("xml") || fileType.equals("json")) {
            return true;
        } else {
            return false;
        }
    }

    public static int chooseDifficulty() throws IOException {
        String difficulty = getInput();
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

    /**
     * Reads user input from command line.
     * @return what user entered as a string
     * @throws IOException
     */
    public static String getInput() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        return reader.readLine();
    }

    public static void printOpening() {
        System.out.println(" \n" +
                "  ||============================================================||\n" +
                "  ||   x    ______   M   _   _   _   x       _     _            ||\n" +
                "  ||   B    | ___ \\     | | | | | |         | |   (_)     BBBx  ||\n" +
                "  ||   B    | |_/ / __ _| |_| |_| | ___  ___| |__  _ _ __       ||\n" +
                "  ||   B    | ___ \\/ _` | __| __| |/ _ \\/ __| '_ \\| | '_ \\      ||\n" +
                "  ||        | |_/ / (_| | |_| |_| |  __/\\__ \\ | | | | |_) |     ||\n" +
                "  ||        \\____/ \\__,_|\\__|\\__|_|\\___||___/_| |_|_| .__/      ||\n" +
                "  ||   x x            x                             | |     x   ||\n" +
                "  ||       x                M         x             |_|         ||\n" +
                "  ||============================================================||\n" +
                " ");
    }

    // TODO: Methods to write to different file types
}
