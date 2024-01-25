package blps.lab2.exceptions;

public class InternalServerException extends RuntimeException {
    public InternalServerException(){}
    public InternalServerException(String message) {
        super(message);
    }
}
