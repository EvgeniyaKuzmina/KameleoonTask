package kameleoon.test.exception;

public class ValidationException extends  RuntimeException {
    public ValidationException(String messages) {
        super(messages);
    }
}
