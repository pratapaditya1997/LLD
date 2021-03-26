package exception;

public class CabNotFoundException extends Exception{
    public CabNotFoundException(String error) {
        super(error);
    }
}
