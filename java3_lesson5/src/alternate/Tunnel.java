package alternate;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    private final Semaphore sm;

    public Tunnel() {
        this.sm = new Semaphore(MainClass.CARS_COUNT / 2);
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            if (!sm.tryAcquire()) {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                sm.acquire();
            }

            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(c.getName() + " закончил этап: " + description);
            sm.release();
        }
    }
}