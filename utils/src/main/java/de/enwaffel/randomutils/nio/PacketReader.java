package de.enwaffel.randomutils.nio;

import de.enwaffel.randomutils.io.ByteBuffer;

public abstract class PacketReader<T extends Packet<?>> {

    private final T type;

    public PacketReader(T type) {
        this.type = type;
    }

    public abstract T read(ByteBuffer data);

    public T getType() {
        return type;
    }

}
