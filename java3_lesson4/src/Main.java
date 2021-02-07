public class Main {
    public static void main(String[] args) {
        Task task = new Task();
        Thread t1 = new Thread(new Consumer(task, "A"));
        Thread t2 = new Thread(new Consumer(task, "B"));
        Thread t3 = new Thread(new Consumer(task, "C"));
        t1.start();
        t2.start();
        t3.start();
    }
}

class Task {

    private final Object mon = new Object();
    private final String[] abc = {"A","B","C","A","B","C","A","B","C","A","B","C","A","B","C"};
    private int counter = 0;

    public void print(final String msg) {
        synchronized (mon) {
            for (int i = 0; i < 5; i++) {
                while (!msg.equals(abc[counter])) {
                    try {
                        mon.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                counter++;
                mon.notifyAll();
                System.out.println(msg);
            }
        }
    }
}

class Consumer implements Runnable {

    private final Task task;
    private final String msg;

    public Consumer(final Task task, final String msg) {
        this.task = task;
        this.msg = msg;
    }

    @Override
    public void run() {
        task.print(msg);
    }
}