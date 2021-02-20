package alternate2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MainClass {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        CyclicBarrier barrier = new CyclicBarrier(CARS_COUNT + 1);

        ArrayBlockingQueue<Car> finished = new ArrayBlockingQueue<>(MainClass.CARS_COUNT);

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), barrier, finished);
            new Thread(cars[i]).start();
        }

        try {
            barrier.await(); //Main поток сообщил, что он готов, 1 поток из 5 готов
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            barrier.await(); //Main поток сообщил, что он готов, 1 поток из 5 готов
            System.out.println(finished.take().getName() + " WIN !!!"); //Тут выставился замок в
            // очереди и ждет первого, кто попадет в очередь
            barrier.await(); //Main поток сообщил, что он готов, 1 поток из 5 готов
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }


        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}