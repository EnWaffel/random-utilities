package de.enwaffel.randomutils.server.packet;

public interface PacketType<T extends Packet<?>> {
    T build(String data);
}
