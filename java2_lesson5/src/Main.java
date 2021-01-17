import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author Dmitriy Bokach
 */
public class Main {
    static final int size = 10_000_000;
    float[] arr = new float[size];

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        main.method1();
        main.method2();
        main.method3(Runtime.getRuntime().availableProcessors());
    }

    private void method1() {
        Arrays.fill(arr, 1);
        long start = System.currentTimeMillis();
        calculate(arr, 0, arr.length);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(IntStream.range(0, arr.length).mapToDouble(i -> arr[i]).sum());
    }

    private void method2() throws InterruptedException {
        Arrays.fill(arr, 1);
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> calculate(arr, 0, size / 2));
        Thread t2 = new Thread(() -> calculate(arr, size / 2, arr.length));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(IntStream.range(0, arr.length).mapToDouble(i -> arr[i]).sum());
    }

    private void method3(final int n) throws InterruptedException {
        if (n > arr.length) {
            method1();
            return;
        }
        Arrays.fill(arr, 1);
        long start = System.currentTimeMillis();
        final Thread[] threads = new Thread[n];
        for (int i = 0; i < n; i++) {
            final int ii = i;
            final int length = size / n * (i + 1);
            threads[i] = new Thread(() -> calculate(arr, ii * (size / n), length));
            threads[i].start();
        }
        Arrays.stream(threads).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        if (arr.length % n != 0) {
            Thread t = new Thread(() -> calculate(arr, arr.length - (arr.length % n), arr.length));
            t.start();
            t.join();
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(IntStream.range(0, arr.length).mapToDouble(i -> arr[i]).sum());
    }

    private void calculate(float[] arr, int firstIndex, int length) {
        for (int i = firstIndex; i < length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

}
