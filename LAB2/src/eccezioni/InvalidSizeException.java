package src.eccezioni;

public class InvalidSizeException extends Exception{
    public InvalidSizeException(){}

    public InvalidSizeException(String msg) {
        super(msg);
    }
}
