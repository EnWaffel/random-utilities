package de.enwaffel.randomutils.buff;

import java.io.InputStream;

public class InByteBuffer extends ByteBuffer {

    private final InputStream is;

    public InByteBuffer(byte[] buffer, InputStream is) {
        super(buffer);
        this.is = is;
    }

    public InByteBuffer(InputStream is) {
        super();
        this.is = is;
    }

    public InByteBuffer(int limit, InputStream is) {
        super(limit);
        this.is = is;
    }

    public byte[] readFully() {
        try {
            while (true) {
                int b = is.read();
                a((byte) b);
                if (b < 0)
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }

    public byte readNext() {
        try {
            int b = is.read();
            if (b != -1) {
                return (byte) b;
            } else {
                return (byte) 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return (byte) 0;
        }
    }

}
