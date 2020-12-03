import animal.Animal;
import animal.Cat;
import animal.Dog;

public class App {
    public static void main(String[] args) {
        Animal dog = new Dog("Пирожок");
        Animal cat = new Cat("Бублик");

        dog.run(10);
        dog.swim(10);

        cat.run(10);
        try {
            cat.swim(10);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("Собак " + Dog.count);
        System.out.println("Кошек " + Cat.count);
        System.out.println("Животных " + Animal.totalCount);
    }
}
