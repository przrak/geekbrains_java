package geekbrains;

public class Application {
    public static void main(String[] args) {
        byte b = 127; // -128 .. 127, 1 байт
        short s = 32767; // -32678 .. 32767, 2 байта
        int i = 2147483647; // -2147483648 .. 2147483647, 4 байта
        long l = 9223372036854775807L; // -9223372036854775808 .. 9223372036854775807, 8 байт
        float f = 0x1.fffffeP+127f; //  -3.4Е +38 .. 3.4Е +38, 4 байта
        double d = 0x1.fffffffffffffP+1023; // -1.7E + 308 .. 1.7Е + 308, 8 байт
        char c = 65535; // 2 байта
        char cc = 'A'; // 2 байта
        boolean bool = true;
        String text = "Hello";

        Application application = new Application();
        System.out.println(application.calculateExpression(1f,2f,3f,4f));
        System.out.println(application.checkTwoNumbers(10, 11));
        System.out.println(application.checkTwoNumbers(5, 11));
        application.echoPositiveOrNegative(-5);
        application.echoPositiveOrNegative(5);
        System.out.println(application.checkPositiveOrNegative(10));
        System.out.println(application.checkPositiveOrNegative(-2));
        application.echoHello("Dmitriy");
    }

    private float calculateExpression(float a, float b, float c, float d) {
        return a * (b + (c / d));
    }

    private boolean checkTwoNumbers(int a, int b) {
        return ((a + b) >= 10 && (a + b) <= 20);
    }

    private void echoPositiveOrNegative(int a) {
        if (a >= 0) {
            System.out.println(a + " is positive");
        }
        else {
            System.out.println(a + " is negative");
        }
    }

    private boolean checkPositiveOrNegative(int a) {
        return a < 0;
    }

    private void echoHello(String name) {
        System.out.println("Привет, " + name);
    }


}
