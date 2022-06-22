package de.enwaffel.randomutils.server.packet.type;

import de.enwaffel.randomutils.server.packet.Packet;

public class StringPacket extends Packet<String> {

    public StringPacket(String data) {
        super(data);
    }

    @Override
    public String writeable() {
        return getData();
    }

}
