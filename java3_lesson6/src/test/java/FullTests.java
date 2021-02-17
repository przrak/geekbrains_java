import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        //Controllers
        MassTestSum.class,
        MassTestTask1.class,
        MassTestTask1Exception.class,
        MassTestTask2.class
})
public class FullTests {
}
