package de.enwaffel.randomutils.io;

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

    public void add(byte b) {
        byte[] bb = new byte[buffer.length + 1];
        System.arraycopy(buffer, 0, bb, 0, buffer.length);
        bb[bb.length - 1] = b;
        buffer = bb;
    }

    public void add(int b) {
        add((byte) b);
    }

    public void clear(boolean holdLength) {
        buffer = holdLength ? new byte[buffer.length] : new byte[0];
    }

    public byte[] getBuffer() {
        return buffer;
    }


}
