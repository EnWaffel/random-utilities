package de.enwaffel.randomutils;

import de.enwaffel.randomutils.server.Server;
import de.enwaffel.randomutils.server.client.Client;
import de.enwaffel.randomutils.server.packet.PacketType;
import de.enwaffel.randomutils.server.packet.type.BytePacket;
import de.enwaffel.randomutils.server.packet.type.StringPacket;

import java.util.Timer;
import java.util.TimerTask;

public class Test {

    public static void main(String[] args) {
        Server server = new Server(1);
        server.start();
        Client client = new Client("localhost", 1);
        client.connect();
        server.addType("string", (PacketType<StringPacket>) data -> null);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                server.sendPacketGlobally(server.getDefaultChannel(), new BytePacket((byte) 1));
                cancel();
            }
        },1000, 1);
    }

}
