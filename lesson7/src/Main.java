import java.util.Random;

public class Main {
    public static void main(final String[] args) {

        Plate plate = new Plate(100);
        Cat[] cats = new Cat[10];
        for (int i = 0; i < cats.length; i++) {
            Cat cat = new Cat("Cat " + i);
            cat.eat(plate, new Random().nextInt(30));
            cats[i] = cat;
        }

        plate.info();
        for (Cat cat : cats) {
            System.out.println(cat.getName() + " satiety " + cat.isSatiety());
        }

    }

}
