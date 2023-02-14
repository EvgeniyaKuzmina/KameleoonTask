package kameleoon.test.exception;

public class SaveException extends  RuntimeException {
    public SaveException(String messages) {
        super(messages);
    }
}
