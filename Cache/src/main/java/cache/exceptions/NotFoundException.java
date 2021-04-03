package cache.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(final String error) {
        super(error);
    }
}
