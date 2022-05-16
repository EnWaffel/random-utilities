package de.enwaffel.randomutils.channel;

import java.util.HashMap;

public abstract class ChannelHolder<C extends Channel> {

    private final C defaultChannel = newChannel("default");
    private final HashMap<String, C> channels = new HashMap<>();

    public void removeChannel(String name) {
        if (!name.equalsIgnoreCase("default")) {
            channels.remove(name);
        }
    }

    public abstract C newChannel(String name);

    public C getDefaultChannel() {
        return defaultChannel;
    }

}
