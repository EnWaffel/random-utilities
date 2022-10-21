package de.enwaffel.randomutils.encode.exception;

import de.enwaffel.projectp.exception.GCException;

public class EncodingException extends GCException {

    public EncodingException() {
        super();
    }

    public EncodingException(String message) {
        super(message);
    }

    public EncodingException(String message, Throwable cause) {
        super(message, cause);
    }

    public EncodingException(Throwable cause) {
        super(cause);
    }

}
