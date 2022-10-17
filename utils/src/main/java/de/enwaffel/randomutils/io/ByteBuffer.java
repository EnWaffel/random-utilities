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

    public void add(byte[] b) {
        for (byte _b : b) add(_b);
    }

    public void add(int[] i) {
        for (int _i : i) add(_i);
    }

    public void clear(boolean holdLength) {
        buffer = holdLength ? new byte[buffer.length] : new byte[0];
    }

    public byte getFirst() {
        return buffer.length > 0 ? buffer[0] : 0x00;
    }

    public byte getLast() {
        return buffer.length > 0 ? buffer[buffer.length - 1] : 0x00;
    }

    public byte[] getBuffer() {
        return buffer;
    }

    public void setBuffer(byte[] buffer) {
        this.buffer = buffer;
    }

    public static ByteBuffer from(String str) {
        ByteBuffer b = new ByteBuffer();
        for (byte _b : str.getBytes()) {
            b.add(_b);
        }
        return b;
    }

    @Override
    public String toString() {
        return new String(buffer);
    }

}
