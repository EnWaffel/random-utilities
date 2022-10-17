package de.enwaffel.randomutils.io;

import de.enwaffel.randomutils.ByteUtil;

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

    public byte[] read() {
        try {
            ByteBuffer totalReadBytes = new ByteBuffer();
            int b = 1;
            int len = -1;

            while (b > 0) {
                b = is.read();
                if (b == 0x01) {
                    if (ByteUtil.byteToDigit(next()) == 0) {
                        byte n = next();
                        StringBuilder tempLen = new StringBuilder();
                        while (n != 0x02) {
                            tempLen.append(ByteUtil.byteToDigit(n));
                            n = next();
                        }
                        len = Integer.parseInt(tempLen.toString());
                        len += 1;
                    }
                }
                if (len > 0) {
                    if (b != 0x01 && b != 0x02 && b != 0x03 && b != 0x04) add(b);
                    len--;
                }
                totalReadBytes.add(b);
                if (b == 0x04) break;
            }
            return getBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    private byte next() {
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
