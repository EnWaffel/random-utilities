package de.enwaffel.randomutils.io;

import de.enwaffel.randomutils.ByteUtil;

import java.io.OutputStream;

public class OutByteBuffer extends ByteBuffer {

    private final OutputStream os;

    public OutByteBuffer(byte[] buffer, OutputStream os) {
        super(buffer);
        this.os = os;
    }

    public OutByteBuffer(OutputStream os) {
        super();
        this.os = os;
    }

    public OutByteBuffer(int limit, OutputStream os) {
        super(limit);
        this.os = os;
    }

    public OutByteBuffer write(byte b) {
        add(b);
        return this;
    }

    public OutByteBuffer write(int b) {
        add(b);
        return this;
    }


    public OutByteBuffer write(byte[] arr) {
        for (byte b : arr) {
            add(b);
        }
        return this;
    }

    public OutByteBuffer write(int[] arr) {
        for (int i : arr) {
            add(i);
        }
        return this;
    }

    /**
     * Writes the buffered data to the OutputStream.
     */
    public void writeToOutput() {
        try {
            os.write(0x01); // write SOH byte (start of heading)
            os.write(0x30);
            os.write(ByteUtil.numberToBytes(getBuffer().length)); // write data length (to ensure the read loop doesn't run forever)
            os.write(0x02);
            os.write(getBuffer()); // write the actual data
            os.write(0x04); // write EOT byte (end of transmission)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
