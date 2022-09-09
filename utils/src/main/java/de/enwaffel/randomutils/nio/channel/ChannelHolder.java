package de.enwaffel.randomutils.nio.channel;

import de.enwaffel.randomutils.io.InByteBuffer;
import de.enwaffel.randomutils.io.OutByteBuffer;
import de.enwaffel.randomutils.nio.Connection;
import de.enwaffel.randomutils.nio.Writable;
import de.enwaffel.randomutils.nio.packet.Packet;
import de.enwaffel.randomutils.nio.packet.PacketDecoder;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class ChannelHolder {

    private final List<String> defaultChannels = new ArrayList<>();

    private final List<Channel> channels = new ArrayList<>();

    public ChannelHolder() {
        defaultChannels.add("default");
        createChannel("default");
    }

    public void createChannel(String name) {
        if (hasChannel(name)) throw new IllegalArgumentException("Channel already exists!");
        Channel channel = new Channel() {
            @Override
            public void write(Connection c, Writable w) {
                OutByteBuffer buffer = c.getOutputBuffer();
                buffer.clear(false);
                JSONObject data = new JSONObject();
                data.put("data", w.toWritableString());
                data.put("channel", getIdentifier());
                buffer.write(data.toString().getBytes());
                buffer.complete();
            }

            @Override
            public void read(Connection c, PacketDecoder<? extends Packet<?>> d) {
                InByteBuffer buffer = c.getInputBuffer();
                buffer.clear(false);
                byte[] bytes = buffer.read();
                //System.out.println(new String(bytes));
            }

            @Override
            public ChannelIdentifier getIdentifier() {
                return new ChannelIdentifier() {
                    @Override
                    public String getName() {
                        return name;
                    }

                    @Override
                    public UUID getUUID() {
                        return null;

                    }
                };
            }
        };
        channels.add(channel);
    }

    public boolean hasChannel(String name) {
        for (Channel channel : channels) {
            if (channel.getIdentifier().getName().equals(name)) return true;
        }
        return false;
    }

    public void removeChannel(String name) {
        if (defaultChannels.contains(name.toLowerCase())) throw new IllegalStateException("Channel cannot be removed!");
        Channel toRemove = null;
        for (Channel channel : channels) {
            if (channel.getIdentifier().getName().equals(name)) {
                toRemove = channel;
            }
        }
        if (toRemove != null) channels.remove(toRemove);
    }

}
