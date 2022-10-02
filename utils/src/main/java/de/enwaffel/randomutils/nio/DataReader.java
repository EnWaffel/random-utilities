package de.enwaffel.randomutils.nio;

import de.enwaffel.randomutils.io.ByteBuffer;

public abstract class DataReader<T> {

    private final T type;

    public DataReader(T type) {
        this.type = type;
    }

    public abstract T read(ByteBuffer buffer);

    public T getType() {
        return type;
    }

}
