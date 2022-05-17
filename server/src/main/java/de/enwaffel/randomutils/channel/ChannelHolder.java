package de.enwaffel.randomutils.channel;

import de.enwaffel.randomutils.client.Connection;
import de.enwaffel.randomutils.packet.Packet;
import de.enwaffel.randomutils.packet.PacketHolder;

import java.io.IOException;
import java.util.HashMap;

public abstract class ChannelHolder<C extends Channel> extends PacketHolder {

    private final C defaultChannel = newChannel("default");
    protected final HashMap<String, C> channels = new HashMap<>();

    protected void writeToChannel(ServerChannel channel, Connection connection, Packet<?> data) throws IOException {
        channel.write(connection.output(), data);
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
