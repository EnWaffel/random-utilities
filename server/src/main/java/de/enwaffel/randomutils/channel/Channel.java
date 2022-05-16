package de.enwaffel.randomutils.channel;

import de.enwaffel.randomutils.client.ClientConnection;
import de.enwaffel.randomutils.client.ClientOutput;
import de.enwaffel.randomutils.packet.Packet;

import java.io.DataInputStream;

public abstract class Channel {

    private final String name;

    public Channel(String name) {
        this.name = name;
    }

    public abstract void read(ClientConnection connection, DataInputStream dis);

    public abstract void write(ClientOutput output, Packet<?> packet);

    public String getName() {
        return name;
    }

}
