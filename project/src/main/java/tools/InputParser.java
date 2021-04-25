package tools;

import game_objects.BoatPosition;
import game_objects.Position;

/**
 * Parses and validates all input from the user.
 */
public class InputParser {

    public static BoatPosition parseBoatPosition(String boatInput) {
        String[] splitBoatPosition = boatInput.split(" ");
        BoatPosition boatPosition;

        boolean vertical = true;
        if(splitBoatPosition[0].charAt(0) == 'h') {
            vertical = false;
        }

        String positionInput = splitBoatPosition[1];
        Position position = parsePosition(positionInput);

        boatPosition = new BoatPosition(vertical, position);
        return boatPosition;
    }

    public static Position parsePosition(String input) {
        Position position;

        String colString = "" + input.charAt(0);
        String rowString = "" + input.charAt(1);
        if(input.length() > 2) rowString += input.charAt(2);

        int col = convertCol(colString);
        int row = convertRow(rowString);

        position = new Position(col, row);
        return position;
    }

    public static boolean validBoatInput(String boatInput) {
        if(boatInput.length() > 5 || boatInput.length() < 4) {
            MessageBank.addMessageLog(MessageBank.ERROR_FORMAT);
            return false;
        }

        String[] splitBoatPosition = boatInput.split(" ");

        char firstChar = boatInput.charAt(0);
        if(firstChar != 'v' && firstChar != 'h') {
            MessageBank.addMessageLog(MessageBank.ERROR_FORMAT);
            return false;
        }

        String position = splitBoatPosition[1];
        if(!validPosition(position)) return false;

        return true;
    }

    public static boolean validPosition(String position) {
        if(position.length() > 3 || position.length() < 2) {
            MessageBank.addMessageLog(MessageBank.ERROR_FORMAT);
            return false;
        }

        if(position.charAt(0) < 'a' || position.charAt(0) > 'j') {
            MessageBank.addMessageLog(MessageBank.ERROR_COLUMN);
            return false;
        }

        String rowString = "" + position.charAt(1);
        if(position.length() > 2) rowString += position.charAt(2);

        try {
            int row = Integer.parseInt(rowString);
            if(row < 1 || row > 10) {
                MessageBank.addMessageLog(MessageBank.ERROR_ROW);
                return false;
            }
        } catch (NumberFormatException e) {
            MessageBank.addMessageLog(MessageBank.ERROR_FORMAT);
            return false;
        }

        return true;
    }

    private static int convertCol(String colString) {
        int col = (int) colString.charAt(0);
        col -= 97;
        return col;
    }

    private static int convertRow(String rowString) {
        int row = Integer.parseInt(rowString);
        row -= 1;
        return row;
    }

    public static int chooseDifficulty(String input) {
        int difficultyScore;

        switch (input) {
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
}
