package de.enwaffel.projectp.exception;

public class GCException extends Exception {

    public GCException() {
        super();
    }

    public GCException(String message) {
        super(message);
    }

    public GCException(String message, Throwable cause) {
        super(message, cause);
    }

    public GCException(Throwable cause) {
        super(cause);
    }

}
