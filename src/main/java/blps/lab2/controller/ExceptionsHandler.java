package blps.lab2.controller;

import blps.lab2.dto.responses.ErrorResponse;
import blps.lab2.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler({AlreadyExistException.class, InvalidDataException.class, NoAvailableGradesException.class})
    public ResponseEntity<ErrorResponse> handleBadRequestExceptions(Exception e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleAuthenticationFailException(AuthenticationFailException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleInternalServerException(InternalServerException e) {
        return ResponseEntity.internalServerError().body(new ErrorResponse(e.getMessage()));
    }
}
