public class MyTest {

    @Anno(description = "Запускаем метод test1")
    public static void test1() {
        System.out.println("test1");
    }

    @Anno()
    private static void test2() {
        System.out.println("test2");
    }

    @Anno(description = "Запускаем метод test3")
    public static void test3() {
        System.out.println("test3");
    }
}
