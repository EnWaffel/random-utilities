package de.enwaffel.randomutils.server;

import de.enwaffel.randomutils.channel.ServerChannel;
import de.enwaffel.randomutils.client.ClientHolder;

public class Server extends ClientHolder<ServerChannel> {


    @Override
    public ServerChannel newChannel(String name) {
        return null;
    }

}
