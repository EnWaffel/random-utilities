package de.enwaffel.randomutils.server.packet.type;

import de.enwaffel.randomutils.server.packet.Packet;

public class BytePacket extends Packet<Byte> {

    public BytePacket(Byte data) {
        super(data);
    }

    @Override
    public String writeable() {
        return String.valueOf(getData());
    }

}
