package de.enwaffel.randomutils.channel;

import de.enwaffel.randomutils.client.Connection;
import de.enwaffel.randomutils.packet.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public abstract class Channel {

    protected final String name;

    public Channel(String name) {
        this.name = name;
    }

    public abstract void read(Connection connection, DataInputStream dis);

    public abstract void write(DataOutputStream dos, Packet<?> packet);

    public String getName() {
        return name;
    }

}
