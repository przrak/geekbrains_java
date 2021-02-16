import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    private CyclicBarrier cb;
    private CountDownLatch raceStartLatch;
    private CountDownLatch raceFinishLatch;
    private ReentrantLock reentrantLock;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, CyclicBarrier cb, CountDownLatch raceStartLatch,
               CountDownLatch raceFinishLatch, ReentrantLock lock) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.cb = cb;
        this.raceStartLatch = raceStartLatch;
        this.raceFinishLatch = raceFinishLatch;
        this.reentrantLock = lock;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            cb.await();
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cb.await();
            raceStartLatch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        if (reentrantLock.tryLock()) {
            System.out.println(this.name + " WIN");
        }
        raceFinishLatch.countDown();
    }
}
