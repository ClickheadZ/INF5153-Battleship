package game_objects;

/**
 * Defines behaviour of a boat.
 */
public class Boat {
    private int size;
    private String name;


    public Boat(int size, String name) {
        this.size = size;
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }
}
