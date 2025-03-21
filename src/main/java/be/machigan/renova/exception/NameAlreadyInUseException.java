package be.machigan.renova.exception;

public class NameAlreadyInUseException extends RuntimeException {
    public NameAlreadyInUseException(String message) {
        super(message);
    }
}
