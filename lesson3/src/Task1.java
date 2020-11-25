import org.w3c.dom.ls.LSOutput;

import java.util.Random;
import java.util.Scanner;

public class Task1 {

    private int attempts = 3;

    public static void main(String[] args) {
        Task1 app = new Task1();
        app.startGame();
    }

    private void startGame() {
        int num = this.guessNumber();
        System.out.println("Загадано число от 0 до 9, попробуйте его отгадать");
        Scanner scanner = new Scanner(System.in);
        boolean inGame = true;
        while (inGame) {
            if (attempts == 0) {
                System.out.println("Игра закончена, вы проиграли:(");
                System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
                if (scanner.hasNextInt()) {
                    int value = scanner.nextInt();
                    if (value == 1) {
                        attempts = 3;
                        System.out.println("Загадано число от 0 до 9, попробуйте его отгадать");
                    } else if (value == 0) {
                        inGame = false;
                    }
                }
            } else {
                System.out.println("У вас " + attempts + " попытки(ка)");
                System.out.println("Введите число:");
                if (scanner.hasNextInt()) {
                    int value = scanner.nextInt();
                    if (value < 0 || value > 9) {
                        System.out.println("Нужно вводить число от 0 до -9!");
                        continue;
                    }
                    if (num == value) {
                        System.out.println("Поздравляем! Вы отгадали!");
                        System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
                        if (scanner.hasNextInt()) {
                            value = scanner.nextInt();
                            if (value == 1) {
                                attempts = 3;
                            } else if (value == 0) {
                                inGame = false;
                            }
                        }
                    } else {
                        System.out.println("Не верное число");
                        if (value > num) {
                            System.out.println("Введенное число больше, чем загаданное");
                        } else {
                            System.out.println("Введенное число меньше, чем загаданное");
                        }
                        subtractAttempts();
                    }
                }
            }
        }
    }

    private int guessNumber() {
        return new Random().nextInt(10);
    }

    private void subtractAttempts() {
        attempts--;
    }
}