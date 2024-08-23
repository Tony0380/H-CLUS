package src.exceptions;

public class EmptySetException extends Exception {
    public EmptySetException() {}

    public EmptySetException(String msg) {
        super(msg);
    }
}
