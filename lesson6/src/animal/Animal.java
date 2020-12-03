package animal;

public abstract class Animal {

    public static int totalCount;

    private final String name;

    public Animal(String name) {
        this.name = name;
        totalCount++;
    }

    public void run(int distance) {
        System.out.println(name + " пробежал " + distance + " м.");
    }

    public void swim(int distance) {
        System.out.println(name + " проплыл " + distance + " м.");
    }
}
