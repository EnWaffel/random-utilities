package de.enwaffel.randomutils.server.channel;

import de.enwaffel.randomutils.io.InByteBuffer;
import de.enwaffel.randomutils.io.OutByteBuffer;
import de.enwaffel.randomutils.server.client.Connection;
import de.enwaffel.randomutils.server.packet.IPacketDecoder;
import de.enwaffel.randomutils.server.packet.Packet;
import org.json.JSONObject;

import java.io.IOException;

public abstract class Channel {

    protected final String name;

    public Channel(String name) {
        this.name = name;
    }

    public void read(Connection connection, IPacketDecoder decoder) throws IOException {
        InByteBuffer buffer = new InByteBuffer(connection.input());
        buffer.read();
        JSONObject data = new JSONObject(new String(buffer.getBuffer()));
        decoder.decode(data.getString("data"));
    }

    protected void write(Connection connection, Packet<?> packet) throws IOException {
        OutByteBuffer buffer = new OutByteBuffer(connection.output());
        JSONObject data = new JSONObject();
        data.put("channel", getName());
        data.put("data", packet.writeable());
        buffer.write(data.toString().getBytes());
        buffer.writeToOutput();
    }

    public String getName() {
        return name;
    }

}
