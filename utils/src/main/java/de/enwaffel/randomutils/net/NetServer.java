package de.enwaffel.randomutils.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class NetServer extends ChannelHolder {

    private ServerSocket serverSocket;
    private final List<NetConnection> connections = new ArrayList<>();
    private Thread acceptThread;

    public NetServer() {
        super();
    }

    public void start() {
        try {
            serverSocket = new ServerSocket();
            init();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to start server.");
        }
    }

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            init();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to start server.");
        }
    }

    public void start(int port, int backlog) {
        try {
            serverSocket = new ServerSocket(port, backlog);
            init();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to start server.");
        }
    }

    public void start(int port, int backlog, InetAddress address) {
        try {
            serverSocket = new ServerSocket(port, backlog, address);
            init();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to start server.");
        }
    }

    public void stop() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to stop server.");
        }
    }

    private void init() {
        outputProvider = new OutputProvider() {
            @Override
            public NetConnection getConnection() {
                return null;
            }

            @Override
            public List<NetConnection> getConnections() {
                return connections;
            }
        };
        createChannel();
        initAcceptThread();
    }

    private void initAcceptThread() {
        acceptThread = new Thread(() -> {
            try {
                while (serverSocket != null && !serverSocket.isClosed()) {
                    Socket socket = serverSocket.accept();
                    NetConnection connection = new NetConnectionImpl(socket);
                    connections.add(connection);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        acceptThread.start();
    }

}
