package controllers;

import controllers.ai.Ai;
import tools.IoHandler;

import java.io.IOException;

/**
 * Handles the logic of the game phase when players are placing their boats and mines.
 */
public class PlacementManager {
    public static void placeBoats(Player player, boolean randomly) throws IOException {
        for(int i=0; i<5; ++i) {
            if(randomly) {
                String placement = Ai.selectRandomPlacement();
            } else {
                String placement = IoHandler.getInput();
            }

            // TODO: actually place boat tiles and stuff
        }
    }
}
