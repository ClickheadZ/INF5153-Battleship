package game_objects;

/**
 * Defines behaviour of a boat tile.
 */
public class BoatTile extends Tile {
    private Boat boat;

    public BoatTile(Boat boat) {
        this.boat = boat;
        this.symbol = 'B';
        this.name = "Boat";
    }
}
