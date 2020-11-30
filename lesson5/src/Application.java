import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        Person[] persons = new Person[5];
        persons[0] = new Person("Jeff Bezos", "CEO",
                "bezyaka@amazon.com", "892312312",
                100000000, 50);
        persons[1] = new Person("Tim Cook", "CEO",
                "timmy@apple.com", "892312313",
                1000000, 55);
        persons[2] = new Person("Bill Gates", "CEO",
                "billy@microsoft.com", "892312314",
                500000, 60);
        persons[3] = new Person("Mark Zuckerberg", "CEO",
                "markovka@facebook.com", "892312315",
                2000000, 35);
        persons[4] = new Person("Satya Nadella", "CEO",
                "satya@microsoft.com", "892312316",
                1500000, 45);

        Arrays.stream(persons).forEach(p -> {
            if (p.getAge() > 40) {
                System.out.println(p);
            }
        });
    }
}
