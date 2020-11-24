import java.util.Random;
import java.util.Scanner;

public class Task2 {

    private final String[] words = {
            "apple", "orange", "lemon", "banana", "apricot", "avocado",
            "broccoli", "carrot", "cherry", "garlic", "grape", "melon",
            "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
            "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"
    };

    public static void main(String[] args) {
        Task2 task2 = new Task2();
        task2.startGame();
    }

    private void startGame() {
        int num = this.guessNumber();
        String guessWord = words[num];
        String templateWord = "###############";
        System.out.println("Загадано слово, попробуйте его отгадать");
        Scanner scanner = new Scanner(System.in);
        boolean inGame = true;
        while (inGame) {
            System.out.println("Введите слово:");
            if (scanner.hasNext()) {
                String typedWord = scanner.next();
                if (typedWord.length() == 0) {
                    System.out.println("Длина слова должна быть больше 0!");
                    continue;
                }
                if (typedWord.equals(guessWord)) {
                    System.out.println("Поздравляем! Вы отгадали!");
                    inGame = false;
                } else {
                    System.out.println("Слово не угадано");
                    templateWord = compareTwoWords(guessWord.toCharArray(), typedWord.toCharArray(), templateWord.toCharArray());
                    System.out.println(templateWord);
                }
            }
        }
    }

    private int guessNumber() {
        return new Random().nextInt(25);
    }

    private String compareTwoWords(char[] guessWord, char[] typedWord, char[] template) {
        for (int i = 0; i < typedWord.length; i++) {
            if (guessWord.length > i && template.length > i && guessWord[i] == typedWord[i]) {
                template[i] = guessWord[i];
            }
        }
        return new String(template);
    }
}
