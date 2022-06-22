package de.enwaffel.randomutils.server.client;

import de.enwaffel.randomutils.server.channel.ChannelHolder;
import de.enwaffel.randomutils.server.channel.ClientChannel;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.Map;

public class Client extends ChannelHolder<ClientChannel> {

    protected boolean running;
    private Thread dataThread;
    protected Socket socket;
    private final String address;
    private final int port;

    public Client(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public void connect() {
        try {
            socket = new Socket(address, port);

            dataThread =  new Thread(this::catch_data, "Client Data Thread");

            running = true;
            dataThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            dataThread.stop();
            if (socket != null)
                socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ClientChannel newChannel(String name) {
        ClientChannel channel = new ClientChannel(name);
        channels.put(name, channel);
        return channel;
    }

    public Connection c() {
        return new Connection(socket);
    }

    private void catch_data() {
        try {
            while (running) {
                if (socket != null) {
                    if (!socket.isClosed() && socket.isConnected()) {
                        DataInputStream dis = new DataInputStream(c().input());
                        for (Map.Entry<String, ClientChannel> set : channels.entrySet()) {
                            ClientChannel channel = set.getValue();
                            if (channel != null && channel.getName().equals("default")) {
                                channel.read(c(), this);
                            }
                        }
                        String str = dis.readUTF();
                        if (!str.isEmpty()) {
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
