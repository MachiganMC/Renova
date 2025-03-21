package be.machigan.renova.exception;

public class TooManyAttemptsException extends RuntimeException {
    public TooManyAttemptsException() {
        super("You've made too many attempts. Please try later");
    }
}
