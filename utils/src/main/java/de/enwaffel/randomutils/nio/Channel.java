package de.enwaffel.randomutils.nio;

public interface Channel {
    void send(Connection connection, Packet<?> packet);
    void send(Connection connection, Packet<?>... packets);
    void send(Packet<?> packet);
    void send(Packet<?>... packets);
    int getId();
}
