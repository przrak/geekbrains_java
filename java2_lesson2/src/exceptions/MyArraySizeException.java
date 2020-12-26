package exceptions;

/**
 * @author Dmitriy Bokach
 */
public class MyArraySizeException extends RuntimeException {

    public MyArraySizeException(String message) {
        super(message);
    }
}
