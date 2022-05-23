package de.enwaffel.randomutils.buff;

import java.util.Arrays;

public class ByteBuffer {

    private byte[] buffer;

    public ByteBuffer(byte[] buffer) {
        this.buffer = buffer;
    }

    public ByteBuffer(int limit) {
        buffer = new byte[limit];
    }

    public ByteBuffer() {
        buffer = new byte[0];
    }

    public void a(byte b) {
        byte[] bb = new byte[buffer.length+1];
        System.arraycopy(buffer, 0, bb, 0, buffer.length);
        bb[bb.length-1] = b;
        buffer = bb;
    }

    public void c() {
        buffer = new byte[buffer.length];
    }

    public byte[] getBuffer() {
        return buffer;
    }

}
