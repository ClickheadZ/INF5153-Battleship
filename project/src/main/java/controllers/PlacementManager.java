package controllers;

import controllers.ai.Ai;
import tools.IoHandler;
import tools.MessageBank;

/**
 * Handles the logic of the game phase when players are placing their boats and mines.
 */
public class PlacementManager {

    public static void placeBoats(Player player, boolean randomly) {
        for(int i=0; i<5; ++i) {
            String boatInput;

            if(randomly) {
                boatInput = Ai.selectRandomPlacement();
            } else {
                player.printPlayerGrid();
                System.out.print("Select where you want to place your " +
                        player.boats[i].getName() + " : ");
                boatInput = IoHandler.getInput();
            }

            if(!player.placeBoat(i, boatInput)) {
                --i;
                if(player.isUser && randomly) MessageBank.resetLogs();
            }

            if(!player.isUser) MessageBank.resetLogs();

            if(MessageBank.hasMessage()) MessageBank.printMessageLog();
        }
    }

    public static void placeMines(Player player, boolean randomly) {
        for(int i=0; i<5; ++i) {
            String position;

            if(randomly) {
                position = Ai.selectRandomPosition();
            } else {
                player.printPlayerGrid();
                System.out.print("Select where you want to place your mine : ");
                position = IoHandler.getInput();
            }

            if(!player.placeMine(position)) {
                --i;
                if(player.isUser && randomly) MessageBank.resetLogs();
            }

            if(!player.isUser) MessageBank.resetLogs();

            if(MessageBank.hasMessage()) MessageBank.printMessageLog();
        }
    }
}
