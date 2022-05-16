package de.enwaffel.randomutils.client;

import java.io.IOException;
import java.net.Socket;

public class ClientConnection {

    private final Socket socket;

    public ClientConnection(Socket socket) {
        this.socket = socket;
    }

    public ClientOutput output() throws IOException {
        return new ClientOutput(this, socket.getOutputStream());
    }

}
