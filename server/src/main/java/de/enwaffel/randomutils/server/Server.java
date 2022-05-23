package de.enwaffel.randomutils.server;

import de.enwaffel.randomutils.channel.ServerChannel;
import de.enwaffel.randomutils.client.ClientHolder;
import de.enwaffel.randomutils.client.Connection;
import de.enwaffel.randomutils.packet.Packet;

import java.net.ServerSocket;

public class Server extends ClientHolder<ServerChannel> {

    private final int port;

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void start() {
        if (running)
            stop();
        try {
            socket = new ServerSocket(port);
            super.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendPacketGlobally(ServerChannel channel, Packet<?> packet) {
        try {
            for (int i = 0;i < clients.size();i++) {
                Connection connection = clients.get(i);
                if (connection != null) {
                    writeToChannel(channel, connection, packet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ServerChannel newChannel(String name) {
        ServerChannel channel = new ServerChannel(name);
        channels.put(name, channel);
        return channel;
    }

}
