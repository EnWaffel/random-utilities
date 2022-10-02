package de.enwaffel.randomutils.nio;

import java.util.ArrayList;
import java.util.List;

public class ChannelHolder {

    public boolean autoRegisterChannels = false;
    protected final List<Channel> channels = new ArrayList<>();
    protected int channelIdEnumerator = 0;
    protected OutputProvider outputProvider;

    public Channel createChannel() {
        Channel newChannel = new ChannelImpl(channelIdEnumerator, outputProvider);
        channels.add(newChannel);
        channelIdEnumerator++;
        return newChannel;
    }

    public Channel createChannel(int id) {
        if (channelIdEnumerator < id) channelIdEnumerator = id;
        Channel newChannel = new ChannelImpl(channelIdEnumerator, outputProvider);
        channels.add(newChannel);
        return newChannel;
    }

}
