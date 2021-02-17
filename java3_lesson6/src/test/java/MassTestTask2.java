import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MassTestTask2 {
    private boolean expected;
    private int[] arr;
    private Task task;

    public MassTestTask2(final boolean expected, final int[] arr) {
        this.expected = expected;
        this.arr = arr;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {true, new int[]{1,1,1,1,4,4,1,4,4}},
                {false, new int[]{1,1,1,1,1,1}},
                {false, new int[]{4,4,4,4}},
                {false, new int[]{1,4,4,1,1,4,3}},
        });
    }

    @Before
    public void prepare() {
        task = new Task();
    }

    @Test
    public void testTask2() {
        Assert.assertEquals(expected, task.task2(arr));
    }
}
