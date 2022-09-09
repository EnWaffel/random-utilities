package de.enwaffel.randomutils.nio.channel;

import de.enwaffel.randomutils.nio.Connection;
import de.enwaffel.randomutils.nio.Writable;
import de.enwaffel.randomutils.nio.packet.Packet;
import de.enwaffel.randomutils.nio.packet.PacketDecoder;

public interface Channel {
    void write(Connection c, Writable w);
    void read(Connection c, PacketDecoder<? extends Packet<?>> d);
    ChannelIdentifier getIdentifier();
}
