package de.enwaffel.randomutils.net;

import de.enwaffel.randomutils.ByteUtil;
import de.enwaffel.randomutils.io.ByteBuffer;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class PacketHolder {

    protected final List<PacketReader> readers = new ArrayList<>();
    protected int readerIdEnumerator = 0;

    public PacketHolder() {
        addReader(new PacketReader(StringPacket.class) {
            @Override
            public Packet<?> read(ByteBuffer data) {
                return new StringPacket(new String(data.getBuffer()));
            }
        });
    }

    public void addReader(PacketReader reader) {
        reader.id = readerIdEnumerator;
        readers.add(reader);
    }

    public void addReader(PacketReader reader, int id) {
        if (readerIdEnumerator < id) readerIdEnumerator = id;
        reader.id = id;
        readers.add(reader);
    }

    public PacketReader getReader(Class<? extends Packet<?>> clazz) {
        for (PacketReader reader : readers) {
            if (reader.getType().equals(clazz)) {
                return reader;
            }
        }
        return readers.get(0);
    }

    protected Packet<?> readPacket(ByteBuffer buffer) {
        if (buffer.getBuffer().length > 0) {
            for (int i = 0;i < readers.size();i++) {
                PacketReader reader = readers.get(i);
                if (reader != null) {
                    if (reader.id == ByteUtil.byteToDigit(buffer.getFirst())) {
                        buffer.setBuffer(ArrayUtils.remove(buffer.getBuffer(), 0));
                        buffer.setBuffer(ArrayUtils.remove(buffer.getBuffer(), buffer.getBuffer().length - 1));
                        return reader.read(buffer);
                    }
                }
            }
        }
        return new Packet<ObjectUtils.Null>(){};
    }

}
