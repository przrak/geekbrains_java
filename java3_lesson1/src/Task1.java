import java.util.Arrays;

/**
 * @author Dmitriy Bokach
 */
public class Task1 {
    public static void main(String[] args) {

        String[] strings = new String[] {"a","b","c","d","e"};
        Integer[] integers = new Integer[] {1,2,3,4,5};

        System.out.println(Arrays.toString(swapTwoElementByIndex(strings, 0, 4)));
        System.out.println(Arrays.toString(swapTwoElementByIndex(integers, 1, 3)));

    }

    private static Object[] swapTwoElementByIndex(final Object[] objects,
                                                  final int firstIndex,
                                                  final int secondIndex
    ) {
        if (firstIndex < 0 || secondIndex < 0
                || firstIndex >= objects.length || secondIndex >= objects.length) {
            throw new IllegalArgumentException("Index less 0 or index more then objects size");
        }
        Object temp = objects[firstIndex];
        objects[firstIndex] = objects[secondIndex];
        objects[secondIndex] = temp;
        return objects;
    }


}
