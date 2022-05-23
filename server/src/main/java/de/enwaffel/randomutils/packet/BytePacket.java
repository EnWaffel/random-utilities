package de.enwaffel.randomutils.packet;

public class BytePacket extends Packet<Byte> {

    public BytePacket(Byte data) {
        super(data);
    }

    @Override
    public String writeable() {
        return String.valueOf(getData());
    }

}
