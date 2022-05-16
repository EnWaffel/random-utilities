package de.enwaffel.randomutils.client;

import java.io.DataOutputStream;
import java.io.OutputStream;

public class ClientOutput extends DataOutputStream {

    private final ClientConnection connection;

    public ClientOutput(ClientConnection connection, OutputStream out) {
        super(out);
        this.connection = connection;
    }

    public ClientConnection getConnection() {
        return connection;
    }

}
