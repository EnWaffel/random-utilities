package de.enwaffel.projectp.exception;

public class APICommunicationException extends GCException {

    public APICommunicationException() {
        super();
    }

    public APICommunicationException(String message) {
        super(message);
    }

    public APICommunicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public APICommunicationException(Throwable cause) {
        super(cause);
    }

}
