package de.enwaffel.randomutils.io;

import java.lang.reflect.Array;

public class Buffer<T> {

    private final Class<T> type;
    private T[] buffer;

    public Buffer(Class<T> type, T[] buffer) {
        this.type = type;
        this.buffer = buffer;
    }

    public Buffer(Class<T> type, int limit) {
        this.type = type;
        _newBuffer(limit);
    }

    public Buffer(Class<T> type) {
        this.type = type;
        _newBuffer(0);
    }

    public void add(T b) {
        T[] bb = _newBuffer(buffer.length + 1);
        System.arraycopy(buffer, 0, bb, 0, buffer.length);
        bb[bb.length - 1] = b;
        buffer = bb;
    }

    public void clear(boolean holdLength) {
        if (holdLength) _newBuffer(buffer.length); else _newBuffer(0);
    }

    public T[] getBuffer() {
        return buffer;
    }

    private T[] _newBuffer(int length) {
        T[] newBuffer = (T[]) Array.newInstance(type, length);
        buffer = newBuffer;
        return newBuffer;
    }

}
