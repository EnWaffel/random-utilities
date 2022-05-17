package de.enwaffel.randomutils.client;

import de.enwaffel.randomutils.channel.Channel;
import de.enwaffel.randomutils.channel.ChannelHolder;
import de.enwaffel.randomutils.channel.ServerChannel;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class ClientHolder<C extends Channel> extends ChannelHolder<ServerChannel> {

    private boolean running;

    private Thread acceptThread;
    private Thread dataThread;
    protected ServerSocket socket;
    private final List<Connection> clients = new ArrayList<>();

    public void start() {
        acceptThread = new Thread(this::catch_clients, "Server Client Accept Thread");
        dataThread =  new Thread(this::catch_data, "Server Data Thread");

        running = true;
        acceptThread.start();
        dataThread.start();
    }

    public void stop() {
        acceptThread.stop();
        dataThread.stop();
    }

    public void catch_clients() {
        try {
            while (running) {
                if (socket != null && !socket.isClosed() && socket.isBound()) {
                    Socket s = socket.accept();
                    if (s != null) {
                        Connection conn = new Connection(s);
                        clients.add(conn);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void catch_data() {
        try {
            while (running) {
                if (socket != null) {
                    for (int i = 0;i < clients.size();i++) {
                        Connection conn = clients.get(i);
                        if (conn != null && !conn.s().isClosed() && conn.s().isConnected()) {
                            DataInputStream dis = new DataInputStream(conn.input());
                            String str = dis.readUTF();
                            if (!str.isEmpty() && !str.isBlank()) {
                                for (Map.Entry<String, ServerChannel> set : channels.entrySet()) {
                                    ServerChannel channel = set.getValue();
                                    if (channel != null && channel.getName().equals(str)) {
                                        channel.read(conn, dis);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
