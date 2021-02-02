package Task3;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitriy Bokach
 */
public class Box<T extends Fruit> {

    private T fruit;

    private final List<T> fruits = new ArrayList<>();

    public Box() {
    }

    public void addFruitToBox(T fruit) {
        fruits.add(fruit);
    }

    public List<T> getFruits() {
        return fruits;
    }

    public float getWeight() {
        return fruits.stream()
                .map(Fruit::getWeight)
                .reduce(0f, Float::sum);
    }

    public boolean compare(Box<? extends Fruit> o) {
        return Math.abs(this.getWeight() - o.getWeight()) < 0.00001;
    }

    public void addAllFruitsToBox(Box<T> box) {
        fruits.addAll(box.getFruits());
        box.getFruits().clear();
    }
}
