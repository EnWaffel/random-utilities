package de.enwaffel.randomutils.io;

import de.enwaffel.randomutils.ByteUtil;

import java.io.OutputStream;

public class DataSetOutByteBuffer extends ByteBuffer {

    private final OutputStream os;

    public DataSetOutByteBuffer(byte[] buffer, OutputStream os) {
        super(buffer);
        this.os = os;
    }

    public DataSetOutByteBuffer(OutputStream os) {
        super();
        this.os = os;
    }

    public DataSetOutByteBuffer(int limit, OutputStream os) {
        super(limit);
        this.os = os;
    }

    public DataSetOutByteBuffer write(DataSet dataSet) {
        add(0x02);
        _write(dataSet.getKey().getBytes());
        byte[] bytes = dataSet.getData().toString().getBytes();
        add(0x03);
        add(0x02);
        _write(ByteUtil.numberToBytes(bytes.length));
        add(0x03);
        add(0x02);
        _write(bytes);
        add(0x03);
        return this;
    }

    private DataSetOutByteBuffer _write(byte[] arr) {
        for (byte b : arr) {
            add(b);
        }
        return this;
    }

    public void writeToOutput() {
        try {
            os.write(0x01); // write SOH byte (start of heading)
            os.write(0x31);
            os.write(0x02);
            os.write(ByteUtil.numberToBytes(getBuffer().length)); // write data length (to ensure the read loop doesn't run forever)
            os.write(0x01);
            os.write(getBuffer()); // write the actual data
            os.write(0x04); // write EOT byte (end of transmission)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
