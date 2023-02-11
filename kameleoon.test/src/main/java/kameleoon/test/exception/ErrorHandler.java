package kameleoon.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handlerConflictException(ConflictException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .reason("Data conflict")
                .status(HttpStatus.CONFLICT)
                .build();
    }
}
