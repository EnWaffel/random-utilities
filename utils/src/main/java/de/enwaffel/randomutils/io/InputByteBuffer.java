package de.enwaffel.randomutils.io;

import java.io.InputStream;

public class InputByteBuffer extends ByteBuffer {

    private final InputStream is;

    public InputByteBuffer(byte[] buffer, InputStream is) {
        super(buffer);
        this.is = is;
    }

    public InputByteBuffer(InputStream is) {
        super();
        this.is = is;
    }

    public InputByteBuffer(int limit, InputStream is) {
        super(limit);
        this.is = is;
    }

    public byte[] read() {
        try {
            int b = 0;
            while (b >= 0) {
                b = is.read();
                add(b);
            }
            return getBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

}