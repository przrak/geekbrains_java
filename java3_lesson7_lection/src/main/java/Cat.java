public class Cat {

    private String name;
    private int age;
    private String color;

    public Cat(
            String name,
            int age,
            String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public final synchronized void info(int a) {
        System.out.printf("Cat %s %d %s\n", name, age, color);
    }

    private void meow() {
        System.out.printf("Cat %s meow\n", name);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }
}
