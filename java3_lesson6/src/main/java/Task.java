import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task {

    public int[] task1(int[] arr) {
        final int four = 4;
        List<Integer> result = new ArrayList<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == four) {
                break;
            }
            result.add(arr[i]);
            if (i == 0) {
                throw new RuntimeException("Array without 4");
            }
        }
        Collections.reverse(result);
        int[] res = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        return res;
    }

    public boolean task2(int[] arr) {
        final int four = 4;
        final int one = 1;
        boolean fourExists = false;
        boolean oneExists = false;
        for (int j : arr) {
            if (j == four) {
                fourExists = true;
            } else if (j == one) {
                oneExists = true;
            }
            if (j != four && j != one) {
                return false;
            }
        }
        return fourExists && oneExists;
    }

}
