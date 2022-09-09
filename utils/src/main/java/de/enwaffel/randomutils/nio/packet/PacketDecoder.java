package de.enwaffel.randomutils.nio.packet;

public interface PacketDecoder<T extends Packet<?>> {
    T decode(String data);
}
