package de.enwaffel.projectp.exception;

public class NotRegisteredException extends GCException {

    public NotRegisteredException() {
        super();
    }

    public NotRegisteredException(String message) {
        super(message);
    }

    public NotRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotRegisteredException(Throwable cause) {
        super(cause);
    }

}
