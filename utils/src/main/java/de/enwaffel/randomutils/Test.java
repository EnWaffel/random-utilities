package de.enwaffel.randomutils;

import de.enwaffel.randomutils.client.Client;
import de.enwaffel.randomutils.packet.BytePacket;
import de.enwaffel.randomutils.server.Server;

import java.util.Timer;
import java.util.TimerTask;

public class Test {

    public static void main(String[] args) {
        Server server = new Server(1);
        server.start();
        Client client = new Client("localhost", 1);
        client.connect();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                server.sendPacketGlobally(server.getDefaultChannel(), new BytePacket("a".getBytes()[0]));
                System.out.println("packet sent!");
                cancel();
            }
        },0, 3000);
    }

}
