package blps.lab2.exceptions;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(){}
    public InvalidDataException(String message) {
        super(message);
    }
}
