import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalcTest {

    private Calc calc;

    @Before
    public void prepare() {
        calc = new Calculator();
    }

    @Test
    public void testAdd() {
        Assert.assertEquals(5, calc.sum(2,3));
    }

    @Test
    public void testMul() {
        Assert.assertEquals(6, calc.mul(2,3));
    }

    @Test
    public void testDiv() {
        Assert.assertEquals(5, calc.div(10,2));
    }

    @Test(expected = ArithmeticException.class)
    public void testDivException() {
        calc.div(12, 0);
    }


}
