package controllers.ai;

import java.util.Random;

public class Ai {
    public static String selectRandomPlacement() {
        Random rand = new Random();
        int randomBool = rand.nextInt(2);
        if(randomBool == 1) {
            return "v " + selectRandomPosition();
        } else {
            return "h " + selectRandomPosition();
        }
    }

    public static String selectRandomPosition() {
        Random rand = new Random();
        int randomRow = rand.nextInt(10) + 1;
        char randomCol = (char) (rand.nextInt(10) + 97);

        return "" + randomCol + randomRow;
    }
}
