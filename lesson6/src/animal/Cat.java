package animal;

public class Cat extends Animal {

    public static int count;

    public Cat(String name) {
        super(name);
        count++;
    }

    @Override
    public void run(int distance) {
        if (distance > 200) {
            throw new RuntimeException("Кот не может пробежать больше 200 метров!");
        }
        super.run(distance);
    }

    @Override
    public void swim(int distance) {
        throw new RuntimeException("Кот не умеет плавать!");
    }

}
