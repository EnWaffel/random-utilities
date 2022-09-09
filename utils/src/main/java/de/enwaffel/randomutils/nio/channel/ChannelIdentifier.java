package de.enwaffel.randomutils.nio.channel;

import java.util.UUID;

public interface ChannelIdentifier {
    String getName();
    UUID getUUID();
    @Override
    String toString();
}
