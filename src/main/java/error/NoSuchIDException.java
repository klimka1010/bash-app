package error;

public class NoSuchIDException extends RuntimeException{
    public NoSuchIDException(String message) {
        super(message);
    }
}
