import exceptions.MyArraySizeException;
import exceptions.MyDataArrayException;

/**
 * @author Dmitriy Bokach
 */
public class App {
    public static void main(final String[] args) {
        final App app = new App();
        final String[][] array = new String[][]{
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };
        try {
            System.out.println("Sum elements result - " + app.format(array));
        } catch (MyArraySizeException | MyDataArrayException e) {
            System.out.println(e.getMessage());
        }
    }

    private int format(final String[][] twoDimensionalArray) throws MyArraySizeException, MyDataArrayException {
        final String exceptionMessage = "Array length must be 4x4";
        if (twoDimensionalArray.length != 4) {
            throw new MyArraySizeException(exceptionMessage);
        }
        int sum = 0;
        for (int i = 0; i < twoDimensionalArray.length; i++) {
            if (twoDimensionalArray[i].length != 4) {
                throw new MyArraySizeException(exceptionMessage);
            }
            for (int j = 0; j < twoDimensionalArray[i].length; j++) {
                try {
                    sum += Integer.parseInt(twoDimensionalArray[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyDataArrayException("Exception message: " + e.getMessage() + " we get NumberFormatException" +
                            " \nInvalid data index: i = " + i + " and j = " + j + " \nSum before exception: " + sum);
                }
            }
        }
        return sum;
    }
}
