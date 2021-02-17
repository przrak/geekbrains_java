import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MassTestSum {
    private int sum;
    private int a;
    private int b;
    private Calc calc;

    public MassTestSum(
            int sum,
            int a,
            int b) {
        this.sum = sum;
        this.a = a;
        this.b = b;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {5, 2, 3},
//                {new int[]{5,6,7}, 2, 3},
                {15, 2, 13},
                {25, 2, 23},
                {-5, -2, -3},
                {5, -2, 7},
                {99, 100, -1},
                {0, 2, -2},
        });
    }

    @Before
    public void prepare() {
        calc = new Calculator();
    }

    @Test
    public void testAdd() {
        Assert.assertEquals(sum, calc.sum(a, b));
    }
}
