package de.enwaffel.randomutils.net;

import de.enwaffel.randomutils.ByteUtil;
import de.enwaffel.randomutils.io.ByteBuffer;
import org.apache.commons.lang3.ArrayUtils;

import java.io.OutputStream;

class NetChannelImpl extends NetChannel {

    private final int id;
    private final OutputProvider provider;
    private final ChannelHolder holder;
    private ChannelInputListener[] listeners = new ChannelInputListener[0];

    protected NetChannelImpl(int id, OutputProvider provider, ChannelHolder holder) {
        this.id = id > -1 ? id : 0;
        this.provider = provider;
        this.holder = holder;
    }

    @Override
    public void send(NetConnection connection, Packet<?> packet) {
        try {
            String data = packet.get();
            OutputStream o = connection.getOutput();
            ByteBuffer buffer = new ByteBuffer();
            buffer.add(0x01);
            buffer.add(ByteUtil.numberToBytes(id));
            buffer.add(0x02);
            buffer.add(ByteUtil.numberToBytes(holder.getReader((Class<? extends Packet<?>>) packet.getClass()).id));
            buffer.add(data.getBytes());
            buffer.add(0x04);
            o.write(buffer.getBuffer());
        } catch (Exception e) {
            System.err.println("Failed to write: " + e.getMessage());
        }
    }

    @Override
    public void send(NetConnection connection, Packet<?>... packets) {
        for (Packet<?> packet : packets) send(connection, packet);
    }

    @Override
    public void send(Packet<?> packet) {
        if (provider != null) {
            if (provider.getConnections() == null) {
                send(provider.getConnection(), packet);
            } else {
                for (NetConnection c : provider.getConnections()) {
                    send(c, packet);
                }
            }
        }
    }

    @Override
    public void send(Packet<?>... packets) {
        if (provider != null) {
            if (provider.getConnections() == null) {
                send(provider.getConnection(), packets);
            } else {
                for (NetConnection c : provider.getConnections()) {
                    send(c, packets);
                }
            }
        }
    }

    @Override
    public void listen(ChannelInputListener listener) {
        listeners = ArrayUtils.add(listeners, listener);
    }

    @Override
    public void unlisten(ChannelInputListener listener) {
        listeners = ArrayUtils.removeElement(listeners, listener);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void readInput(ChannelHolder holder, NetConnection connection) {
        try {
            if (connection != null) {
                if (connection.isOpen() && connection.getInput().available() > 0) {
                    ByteBuffer buffer = new ByteBuffer();
                    int b = 0;
                    while (b != 0x04) {
                        b = connection.getInput().read();
                        buffer.add(b);
                    }
                    Packet<?> packet = holder.readPacket(buffer);
                    for (ChannelInputListener listener : listeners) {
                        listener.onPacketReceived(connection, packet);
                        listener.onPacketReceived(connection, buffer);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
