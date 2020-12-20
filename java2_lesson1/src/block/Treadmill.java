package block;

/**
 * @author Dmitriy Bokach
 */
public class Treadmill implements Blockage {

    private final int length;

    public Treadmill(final int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
