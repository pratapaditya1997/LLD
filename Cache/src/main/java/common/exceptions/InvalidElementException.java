package common.exceptions;

public class InvalidElementException extends RuntimeException {
    public InvalidElementException(String error) {
        super(error);
    }
}
