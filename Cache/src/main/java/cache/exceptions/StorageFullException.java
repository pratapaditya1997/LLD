package cache.exceptions;

public class StorageFullException extends RuntimeException {
    public StorageFullException(final String error) {
        super(error);
    }
}
