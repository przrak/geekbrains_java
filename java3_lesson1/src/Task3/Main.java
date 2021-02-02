package Task3;

/**
 * @author Dmitriy Bokach
 */
public class Main {
    public static void main(String[] args) {
        Box<Orange> box1 = new Box<>();
        box1.addFruitToBox(new Orange());
        box1.addFruitToBox(new Orange());
        box1.addFruitToBox(new Orange());

        Box<Orange> box2 = new Box<>();
        box2.addFruitToBox(new Orange());
        box2.addFruitToBox(new Orange());
        box2.addFruitToBox(new Orange());

        Box<Apple> box3 = new Box<>();
        box3.addFruitToBox(new Apple());
        box3.addFruitToBox(new Apple());
        box3.addFruitToBox(new Apple());

        System.out.println(box1.compare(box3));
        System.out.println(box1.compare(box2));

        box1.addAllFruitsToBox(box2);

        System.out.println(box1.getFruits());

        System.out.println(box2.getFruits());

    }
}
