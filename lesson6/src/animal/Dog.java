package animal;

public class Dog extends Animal {

    public static int count;

    public Dog(String name) {
        super(name);
        count++;
    }

    @Override
    public void run(int distance) {
        if (distance > 500) {
            throw new RuntimeException("Собака не может пробежать больше 500 метров!");
        }
        super.run(distance);
    }

    @Override
    public void swim(int distance) {
        if (distance > 10) {
            throw new RuntimeException("Собака не может проплыть больше 10 метров!");
        }
        super.swim(distance);
    }
}
