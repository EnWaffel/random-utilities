package de.enwaffel.randomutils.nio.packet;

import de.enwaffel.randomutils.nio.Writable;

public abstract class Packet<T> implements Writable {

    protected T data;

    public Packet(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
