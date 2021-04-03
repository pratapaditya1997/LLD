package common.exceptions;

public class InvalidNodeException extends RuntimeException {
    public InvalidNodeException(String error) {
        super(error);
    }
}
