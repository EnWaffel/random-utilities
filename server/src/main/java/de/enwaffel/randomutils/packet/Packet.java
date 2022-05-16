package de.enwaffel.randomutils.packet;

public abstract class Packet<T> {

    private T data;

    public Packet(T data) {
        this.data = data;
    }

    public abstract String writeable();

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
