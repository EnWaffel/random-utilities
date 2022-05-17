package de.enwaffel.randomutils.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection {

    private final Socket socket;

    public Connection(Socket socket) {
        this.socket = socket;
    }

    public Socket s() {
        return socket;
    }

    public DataInputStream input() throws IOException {
        return new DataInputStream(socket.getInputStream());
    }

    public DataOutputStream output() throws IOException {
        return new DataOutputStream(socket.getOutputStream());
    }

}
