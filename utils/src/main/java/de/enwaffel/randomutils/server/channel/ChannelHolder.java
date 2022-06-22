package de.enwaffel.randomutils.server.channel;

import de.enwaffel.randomutils.server.client.Connection;
import de.enwaffel.randomutils.server.packet.Packet;
import de.enwaffel.randomutils.server.packet.PacketHolder;

import java.io.IOException;
import java.util.HashMap;

public abstract class ChannelHolder<C extends Channel> extends PacketHolder {

    protected final HashMap<String, C> channels = new HashMap<>();
    private final C defaultChannel = newChannel("default");

    protected void writeToChannel(ServerChannel channel, Connection connection, Packet<?> data) throws IOException {
        channel.write(connection, data);
    }

    public void removeChannel(String name) {
        if (!name.equalsIgnoreCase("default")) {
            channels.remove(name);
        }
    }

    public abstract C newChannel(String name);

    public C getDefaultChannel() {
        return defaultChannel;
    }

}
