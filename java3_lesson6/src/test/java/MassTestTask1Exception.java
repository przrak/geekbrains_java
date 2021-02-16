import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MassTestTask1Exception {
    private int[] expected;
    private int[] arr;
    private Task task;

    public MassTestTask1Exception(final int[] expected, final int[] arr) {
        this.expected = expected;
        this.arr = arr;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{3,4}, new int[]{3,1,7}},
        });
    }

    @Before
    public void prepare() {
        task = new Task();
    }

    @Test(expected = RuntimeException.class)
    public void testTask1() {
        Assert.assertArrayEquals(expected, task.task1(arr));
    }
}
