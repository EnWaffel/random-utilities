package de.enwaffel.randomutils.io.network.packet;

public interface PacketDecoder<T extends Packet<?>> {
    T decode(String data);
}
