package de.enwaffel.randomutils.net;

import de.enwaffel.randomutils.io.ByteBuffer;

public abstract class PacketReader {

    private final Class<? extends Packet<?>> type;
    protected int id;

    public PacketReader(Class<? extends Packet<?>> type) {
        this.type = type;
    }

    public abstract Packet<?> read(ByteBuffer data);

    public Class<? extends Packet<?>> getType() {
        return type;
    }

}
