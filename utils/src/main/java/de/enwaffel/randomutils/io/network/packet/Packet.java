package de.enwaffel.randomutils.io.network.packet;

import de.enwaffel.randomutils.io.network.Writable;

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
