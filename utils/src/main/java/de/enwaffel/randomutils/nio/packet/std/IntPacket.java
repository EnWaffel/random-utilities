package de.enwaffel.randomutils.nio.packet.std;

import de.enwaffel.randomutils.nio.packet.Packet;

public class IntPacket extends Packet<Integer> {

    public IntPacket(Integer data) {
        super(data);
    }

    @Override
    public String toWritableString() {
        return String.valueOf(data);
    }

}
