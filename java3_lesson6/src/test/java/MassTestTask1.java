import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MassTestTask1 {
    private int[] expected;
    private int[] arr;
    private Task task;

    public MassTestTask1(final int[] expected, final int[] arr) {
        this.expected = expected;
        this.arr = arr;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1,7}, new int[]{1,2,4,4,2,3,4,1,7}},
                {new int[]{2,5}, new int[]{3,4,2,5}},
                {new int[]{3,1,7}, new int[]{4,3,1,7}},
                {new int[]{1,1,1,1,1,1}, new int[]{1,1,4,1,1,1,1,1,1}},
        });
    }

    @Before
    public void prepare() {
        task = new Task();
    }

    @Test
    public void testTask1() {
        Assert.assertArrayEquals(expected, task.task1(arr));
    }
}
