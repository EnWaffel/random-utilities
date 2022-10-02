package de.enwaffel.randomutils.nio;

import java.io.InputStream;
import java.io.OutputStream;

public interface Connection {
    boolean isOpen();
    InputStream getInput();
    OutputStream getOutput();
}
