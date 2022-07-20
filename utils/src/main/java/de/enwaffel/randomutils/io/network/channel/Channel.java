package de.enwaffel.randomutils.io.network.channel;

import de.enwaffel.randomutils.io.network.Connection;
import de.enwaffel.randomutils.io.network.Writable;
import de.enwaffel.randomutils.io.network.packet.PacketDecoder;

public interface Channel {
    void write(Connection c, Writable w);
    void read(Connection c, PacketDecoder d);
    ChannelIdentifier getIdentifier();
}
