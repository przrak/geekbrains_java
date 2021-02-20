package alternate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Task {
    public int[] task1(int[] arr) {
        final int four = 4;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == four) {
                return Arrays.copyOfRange(arr, i + 1, arr.length);
            }
        }
        throw new RuntimeException("Array without 4");
    }

    public boolean task2(int[] arr) {
        final int four = 4;
        final int one = 1;
        boolean fourExists = false;
        boolean oneExists = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != one && arr[i] != four) {
                return false;
            }
            if (arr[i] == one) {
                oneExists = true;
            }
            if (arr[i] == four) {
                fourExists = true;
            }
        }
        return fourExists && oneExists;
    }
}
