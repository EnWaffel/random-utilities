package de.enwaffel.randomutils.net;

public abstract class Packet<T> {

    private T data;
    private Writable writer;

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

    public void setWriter(Writable writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return data.toString();
    }

}
