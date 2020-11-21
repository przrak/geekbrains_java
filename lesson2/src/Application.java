import java.util.Arrays;

public class Application {

    public static void main(String[] args) {
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        negateArray(arr);
        System.out.println(Arrays.toString(arr));

        int[] arr1 = new int[8];
        initArray(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        changeArray(arr2);
        System.out.println(Arrays.toString(arr2));

        int[][] arr3 = new int[8][8];
        initTwoDimensionalArray(arr3);

        int[] arr4 = {2, 10, 4, 3, 24, 6, 7, 8, 11, 1, 88, 16, 13, 81, 9, 23, 14, 5};
        System.out.println(Arrays.toString(findAllMinAndMax(arr4)));

        int[] arr5 = {2, 2, 2, 1, 2, 2, 10, 1};
        System.out.println(checkBalance(arr5));
    }


    public static void negateArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] == 1 ? 0 : 1;
        }
    }

    public static void initArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 3;
        }
    }

    public static void changeArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] < 6 ? arr[i] * 2 : arr[i];
        }
    }

    public static void initTwoDimensionalArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr[i].length - 1; j >= 0; j--) {
                if (j == arr[i].length - 1 - i) {
                    arr[i][j] = 1;
                }
            }
            for (int j = 0; j < arr[i].length; j++) {
                if (i == j) {
                    arr[i][j] = 1;
                }
            }
        }
    }

    public static int[] findAllMinAndMax(int[] arr) {
        int[] result = new int[2];
        Arrays.sort(arr);
        result[0] = arr[0];
        result[1] = arr[arr.length - 1];

        return result;
    }

    public static boolean checkBalance(int[] arr) {
        int[] result = new int[arr.length];
        result[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            result[i] = result[i - 1] + arr[i];
        }

        int[] resultReverse = new int[arr.length];
        resultReverse[arr.length - 1] = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            resultReverse[i] = resultReverse[i + 1] + arr[i];
        }

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < resultReverse.length; j++) {
                if (result[i] == resultReverse[j] && (i == j - 1))
                    return true;
            }
        }

        return false;
    }

}
