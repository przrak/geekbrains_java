import java.util.Arrays;
import java.util.List;

/**
 * @author Dmitriy Bokach
 */
public class Task2 {
    public static void main(String[] args) {
        Integer[] arr = new Integer[] {1,2,3,4,5};
        System.out.println(arrayToList(arr).toString());
    }

    private static List<Object> arrayToList(final Object[] arr) {
        return Arrays.asList(arr);
    }
}
