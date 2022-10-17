package de.enwaffel.randomutils.net;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

public class NetClient extends ChannelHolder {

    private Socket socket;

    public NetClient() {
        super();
    }

    public void connect(int port) {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(port));
            init();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to start client.");
        }
    }

    public void connect(String host, int port) {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(host, port));
            init();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to start client.");
        }
    }

    private void init() {
        outputProvider = new OutputProvider() {
            @Override
            public NetConnection getConnection() {
                return new NetConnectionImpl(socket);
            }

            @Override
            public List<NetConnection> getConnections() {
                return null;
            }
        };
        createChannel();
    }

}
