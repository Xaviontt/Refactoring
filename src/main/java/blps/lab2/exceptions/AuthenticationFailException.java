package blps.lab2.exceptions;

public class AuthenticationFailException extends RuntimeException {
    public AuthenticationFailException(){}
    public AuthenticationFailException(String message) {
        super(message);
    }
}
