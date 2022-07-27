package de.enwaffel.randomutils.io.network.packet.std;

import de.enwaffel.randomutils.io.network.packet.Packet;

public class IntPacket extends Packet<Integer> {

    public IntPacket(Integer data) {
        super(data);
    }

    @Override
    public String toWritableString() {
        return String.valueOf(data);
    }

}
