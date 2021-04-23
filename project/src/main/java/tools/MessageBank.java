package tools;

/**
 * A container class for the various messages the program prints to the screen.
 * Also contains a string that is updated to contain various error messages.
 */
public class MessageBank {
    public static String errorMessage = "";

    public static final String OPENING_SCREEN = """
            \s
              ||============================================================||
              ||   x    ______   M   _   _   _   x       _     _            ||
              ||   B    | ___ \\     | | | | | |         | |   (_)     BBBx  ||
              ||   B    | |_/ / __ _| |_| |_| | ___  ___| |__  _ _ __       ||
              ||   B    | ___ \\/ _` | __| __| |/ _ \\/ __| '_ \\| | '_ \\      ||
              ||        | |_/ / (_| | |_| |_| |  __/\\__ \\ | | | | |_) |     ||
              ||        \\____/ \\__,_|\\__|\\__|_|\\___||___/_| |_|_| .__/      ||
              ||   x x            x                             | |     x   ||
              ||       x                M         x             |_|         ||
              ||============================================================||
            \s""";

    public static final String INSTRUCTIONS_BOAT_PLACEMENT = """
            Instructions : To place a boat, enter v or h depending on if you want the boat to be placed vertically or horizontally, followed by a space and then the letter representing the column,
            immediately followed by the number representing the row.
             i.e : 'v a1' or 'h j10'.

            If the boat is placed vertically, it will continue below the specified position on the grid. If it is placed horizontally, it will continue towards the right of the chosen position.""";

    public static final String INSTRUCTIONS_MINE_PLACEMENT  = """
            Instructions : To place a mine, type the letter representing the column
            followed by the number representing the row.
            i.e : 'j4' or 'g6'.""";

    public static final String ERROR_FORMAT = "ERROR : Incorrect format.";
    public static final String ERROR_BOUNDS = "ERROR : Tried to place a tile out of bounds.";
    public static final String ERROR_COLLISION = "ERROR : Tried to place a tile on another one.";
    public static final String ERROR_BOATS_TOUCH = "ERROR : Boats cannot touch each other.";
}
