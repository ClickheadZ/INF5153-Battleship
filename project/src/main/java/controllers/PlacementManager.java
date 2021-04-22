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
            String placement;

            if(randomly) {
                placement = Ai.selectRandomPlacement();
            } else {
                System.out.print("Select where you want to place your " +
                        player.boats[i].getName() + " : ");
                placement = IoHandler.getInput();
            }

            if(!player.placeBoat(i, placement)) {
                --i;
            }
        }
    }

    public static void placeMines(Player player, boolean randomly) throws IOException {
        for(int i=0; i<5; ++i) {
            String position;

            if(randomly) {
                position = Ai.selectRandomPosition();
            } else {
                System.out.print("Select where you want to place your " +
                        player.boats[i].getName() + " : ");
                position = IoHandler.getInput();
            }

            if(!player.placeMine(position)) {
                --i;
            }
        }
    }
}
