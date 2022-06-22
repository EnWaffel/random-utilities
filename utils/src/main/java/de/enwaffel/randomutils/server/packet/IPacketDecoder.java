package de.enwaffel.randomutils.server.packet;

public interface IPacketDecoder {
    Packet<?> decode(String data);
}
