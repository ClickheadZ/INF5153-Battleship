package tools;

import java.io.IOException;
import java.util.HashMap;

/**
 * Parses and validates all input from the user.
 */
public class InputParser {

    public static BoatPosition parseBoatPosition(String boatInput) throws IOException {
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
            // TODO : inform catch all error msg here
            return false;
        }

        String[] splitBoatPosition = boatInput.split(" ");

        char firstChar = splitBoatPosition[0].charAt(0);
        if(firstChar != 'v' && firstChar != 'h') {
            return false;
        }

        String position = splitBoatPosition[1];
        if(!validPosition(position)) return false;

        return true;
    }

    public static boolean validPosition(String position) {
        if(position.length() > 3) return false;

        if(position.charAt(0) < 'a' || position.charAt(0) > 'j') {
            System.out.println("ERROR : Column letter must be between 'a' and 'j'.");
            return false;
        }

        String rowString = "" + position.charAt(1);
        if(position.length() > 2) rowString += position.charAt(2);

        try {
            int row = Integer.parseInt(rowString);
            if(row < 1 || row > 10) {
                // TODO : catch all error msg
                return false;
            }
        } catch (NumberFormatException e) {
            // TODO : catch all error message
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
}
