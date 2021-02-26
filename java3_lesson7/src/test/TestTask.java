package test;

import main.AfterSuite;
import main.BeforeSuite;
import main.Task;
import main.Test;

public class TestTask {

    private Task task;

    @BeforeSuite
    public void before() {
        task = new Task();
        System.out.println("BeforeSuite complete");
    }

    @Test(priority = 10)
    public void testTask1() {
        final int[] expected = new int[]{1, 7};
        final int[] actual = task.task1(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7});
        if (expected.length != actual.length) {
            throw new RuntimeException("Assert fail! Arrays sizes not equals!");
        }
        for (int i = 0; i < actual.length; i++) {
            if (actual[i] != expected[i]) {
                throw new RuntimeException("Assert fail! Arrays values not equals!");
            }
        }
        System.out.println("TestTask1 complete");
    }

    @Test(priority = 1)
    public void testTask2() {
        final boolean expected = true;
        final boolean actual = task.task2(new int[]{1, 1, 1, 1, 4, 4, 1, 4, 4});
        if (expected != actual) {
            throw new RuntimeException("Assert fail! Values not matched!");
        }
        System.out.println("TestTask2 complete");
    }

    @AfterSuite
    public void after() {
        task = null;
        System.out.println("AfterSuite complete");
    }

}
