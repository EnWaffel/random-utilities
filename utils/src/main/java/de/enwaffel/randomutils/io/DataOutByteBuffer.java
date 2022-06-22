package de.enwaffel.randomutils.io;

import de.enwaffel.randomutils.ByteUtil;

import java.io.OutputStream;

public class DataOutByteBuffer extends ByteBuffer {

    private final OutputStream os;

    public DataOutByteBuffer(byte[] buffer, OutputStream os) {
        super(buffer);
        this.os = os;
    }

    public DataOutByteBuffer(OutputStream os) {
        super();
        this.os = os;
    }

    public DataOutByteBuffer(int limit, OutputStream os) {
        super(limit);
        this.os = os;
    }

    public DataOutByteBuffer write(byte b) {
        add(b);
        return this;
    }

    public DataOutByteBuffer write(byte[] arr) {
        for (byte b : arr) {
            add(b);
        }
        return this;
    }

    public void writeToOutput() {
        try {
            os.write(getBuffer());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}