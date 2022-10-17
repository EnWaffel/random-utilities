package de.enwaffel.randomutils.net;

import de.enwaffel.randomutils.io.ByteBuffer;

public interface ChannelInputListener {
    void onPacketReceived(NetConnection connection, Packet<?> packet);
    void onPacketReceived(NetConnection connection, ByteBuffer buffer);
}
