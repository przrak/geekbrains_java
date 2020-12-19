package block;

/**
 * @author Dmitriy Bokach
 */
public class Wall implements Blockage {

    private final int height;

    public Wall(final int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }
}
