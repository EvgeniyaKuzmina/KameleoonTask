package kameleoon.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handlerConflictException(ConflictException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .status(HttpStatus.CONFLICT)
                .build();
    }

    @ExceptionHandler(ObjectNotFountException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handlerConflictException(ObjectNotFountException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handlerConflictException(ValidationException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @ExceptionHandler(SaveException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handlerConflictException(SaveException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .status(HttpStatus.CONFLICT)
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final List<FieldError> errors = e.getBindingResult().getFieldErrors();
        StringBuilder sb = new StringBuilder();
        for (FieldError error : errors) {
            sb.append(error.getField()).append(" ").append(error.getDefaultMessage());
        }
        return ApiError.builder()
                .message(sb.toString())
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handlerConstraintViolationException(ConstraintViolationException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handlerConflictException(RuntimeException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }
}
