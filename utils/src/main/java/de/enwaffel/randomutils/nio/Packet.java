package de.enwaffel.randomutils.nio;

public class Packet<T> {

    private T data;
    private PacketWriter writer;

    public Packet() {
        data = null;
        writer = () -> "null";
    }

    public Packet(T data) {
        this.data = data;
        writer = data::toString;
    }

    public T getData() {
        return data;
    }

    public String get() {
        return writer.get();
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setWriter(PacketWriter writer) {
        this.writer = writer;
    }

}
