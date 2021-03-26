package exception;

public class RiderNotFoundException extends Exception{
    public RiderNotFoundException(String error) {
        super(error);
    }
}
