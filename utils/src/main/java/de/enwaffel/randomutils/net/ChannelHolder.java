package de.enwaffel.randomutils.net;

import de.enwaffel.randomutils.ByteUtil;
import de.enwaffel.randomutils.io.ByteBuffer;
import org.apache.commons.lang3.ArrayUtils;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ChannelHolder extends PacketHolder {

    public boolean autoRegisterChannels = false;
    protected final List<NetChannel> channels = new ArrayList<>();
    protected int channelIdEnumerator = 0;
    protected OutputProvider outputProvider;
    protected Thread readThread;

    public ChannelHolder() {
        startReadThread();
    }

    private void startReadThread() {
        readThread = new Thread(() -> {
            try {
                while (!Thread.interrupted()) {
                    if (outputProvider != null) {
                        List<NetConnection> connections = new ArrayList<>();

                        if (outputProvider.getConnections() == null) {
                            connections.add(outputProvider.getConnection());
                        } else {
                            for (int i = 0;i < outputProvider.getConnections().size();i++) {
                                NetConnection c = outputProvider.getConnections().get(i);
                                if (c != null) {
                                    connections.add(c);
                                }
                            }
                        }

                        for (NetConnection connection : connections) {
                            if (connection != null && connection.isOpen()) {
                                InputStream in = connection.getInput();
                                if (in.available() > 0 && in.read() == 0x01) {
                                    ByteBuffer channelIdBuffer = new ByteBuffer();
                                    int b = 0;
                                    while (b != 0x02) {
                                        b = in.read();
                                        channelIdBuffer.add(b);
                                    }
                                    channelIdBuffer.setBuffer(ArrayUtils.remove(channelIdBuffer.getBuffer(), channelIdBuffer.getBuffer().length - 1));
                                    int channelId = ByteUtil.bytesToNumber(channelIdBuffer.getBuffer());
                                    if (!connection.getAllowChannels().contains(channelId)) {
                                        boolean channelFound = false;
                                        for (NetChannel channel : channels) {
                                            if (channel.getId() == channelId) {
                                                channelFound = true;
                                                channel.readInput(this, connection);
                                            }
                                        }
                                        if (!channelFound && autoRegisterChannels) createChannel(ByteUtil.bytesToNumber(channelIdBuffer.getBuffer())).readInput(this, connection);
                                    }
                                }
                            }
                        }

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Exception in read thread [" + e.getMessage() + "]; continuing to read...");
            }
        });
        readThread.start();
    }

    public NetChannel createChannel() {
        NetChannel newChannel = new NetChannelImpl(channelIdEnumerator, outputProvider, this);
        channels.add(newChannel);
        channelIdEnumerator++;
        return newChannel;
    }

    public NetChannel createChannel(int id) {
        if (channelIdEnumerator < id) channelIdEnumerator = id;
        NetChannel newChannel = new NetChannelImpl(id, outputProvider, this);
        channels.add(newChannel);
        return newChannel;
    }

    public NetChannel getChannel(int id) {
        for (NetChannel channel : channels) {
            if (channel.getId() == id) {
                return channel;
            }
        }
        return getDefaultChannel();
    }

    public NetChannel getDefaultChannel() {
        return channels.get(0);
    }

}
