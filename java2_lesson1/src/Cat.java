import action.Actionable;

/**
 * @author Dmitriy Bokach
 */
public class Cat implements Actionable {

    private final int length;

    private final int height;

    private final String name;

    public Cat(final int length, final int height, final String name) {
        this.length = length;
        this.height = height;
        this.name = name;
    }

    @Override
    public void jump() {
        System.out.println(name + " прыгает");
    }

    @Override
    public void run() {
        System.out.println(name + " бежит");
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }
}
