package de.enwaffel.randomutils.channel;

import de.enwaffel.randomutils.bytebuff.ByteBuffer;
import de.enwaffel.randomutils.client.ClientConnection;
import de.enwaffel.randomutils.client.ClientOutput;
import de.enwaffel.randomutils.packet.Packet;
import de.enwaffel.randomutils.server.Server;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.nio.charset.StandardCharsets;

public class ServerChannel extends Channel {

    private final Server server;

    public ServerChannel(String name, Server server) {
        super(name);
        this.server = server;
    }

    @Override
    public void read(ClientConnection connection, DataInputStream dis) {

    }

    @Override
    public void write(ClientOutput output, Packet<?> packet) {
        try {
            JSONObject data = new JSONObject();
            data.put("channel", getName());
            data.put("data", packet.writeable());

            ByteBuffer buffer = new ByteBuffer(packet.writeable().getBytes(StandardCharsets.UTF_8));
            output.write(buffer.getBuffer().length);
            output.write(buffer.getBuffer());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
