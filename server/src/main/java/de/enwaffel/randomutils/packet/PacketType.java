package de.enwaffel.randomutils.packet;

public interface PacketType<T extends Packet<?>> {
    T b(String data);
}
