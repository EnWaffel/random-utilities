package de.enwaffel.randomutils.nio;

import de.enwaffel.randomutils.io.OutByteBuffer;

public class ChannelImpl implements Channel {

    private final int id;
    private final OutputProvider provider;

    protected ChannelImpl(int id, OutputProvider provider) {
        this.id = id > -1 ? id : 0;
        this.provider = provider;
    }

    @Override
    public void send(Connection connection, Packet<?> packet) {
        OutByteBuffer buffer = new OutByteBuffer(connection.getOutput());
        buffer.write(String.valueOf(id).getBytes());
        buffer.write("-".getBytes());
        buffer.write(packet.get().getBytes());
        buffer.writeToOutput();
    }

    @Override
    public void send(Connection connection, Packet<?>... packets) {
        for (Packet<?> packet : packets) send(connection, packet);
    }

    @Override
    public void send(Packet<?> packet) {
        if (provider != null) {
            if (!provider.isServer()) {
                send(provider.getConnection(), packet);
            } else {
                for (Connection c : provider.getConnections()) {
                    send(c, packet);
                }
            }
        }
    }

    @Override
    public void send(Packet<?>... packets) {
        if (provider != null) {
            if (!provider.isServer()) {
                send(provider.getConnection(), packets);
            } else {
                for (Connection c : provider.getConnections()) {
                    send(c, packets);
                }
            }
        }
    }

    @Override
    public int getId() {
        return id;
    }

}
