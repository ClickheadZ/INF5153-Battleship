package game_objects.tiles;

/**
 * Defines behaviour of a boat tile.
 */
public class BoatTile extends Tile {
    private int boatId;

    public BoatTile(int boatId) {
        this.boatId = boatId;
        this.symbol = 'B';
        this.name = "Boat";
    }
}
