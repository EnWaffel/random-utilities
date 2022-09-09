package de.enwaffel.randomutils.nio.packet;

import de.enwaffel.randomutils.Namespace;
import de.enwaffel.randomutils.nio.channel.ChannelHolder;
import de.enwaffel.randomutils.nio.packet.std.IntPacket;

import java.util.HashMap;

public abstract class PacketHolder extends ChannelHolder {

    protected final HashMap<Namespace, PacketDecoder<? extends Packet<?>>> decoders = new HashMap<>();

    public PacketHolder() {
        registerDecoder(Namespace.get("std:int_packet"), data -> new IntPacket(Integer.parseInt(data)));
    }

    public void registerDecoder(Namespace enc, PacketDecoder<? extends Packet<?>> decoder) {
        decoders.put(enc, decoder);
    }

    public HashMap<Namespace, PacketDecoder<? extends Packet<?>>> getDecoders() {
        return decoders;
    }

}
