package de.enwaffel.randomutils.io.network.channel;

import java.util.UUID;

public interface ChannelIdentifier {
    String getName();
    UUID getUUID();
    @Override
    String toString();
}
