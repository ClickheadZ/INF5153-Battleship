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

    public int getBoatId() {
        return boatId;
    }

    public boolean hasSameId(BoatTile other) {
        if(this.boatId == other.getBoatId()) {
            return true;
        } else {
            return false;
        }
    }
}
