package de.enwaffel.randomutils.channel;

import de.enwaffel.randomutils.buff.ByteBuffer;
import de.enwaffel.randomutils.client.Connection;
import de.enwaffel.randomutils.packet.Packet;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public abstract class Channel {

    protected final String name;

    public Channel(String name) {
        this.name = name;
    }

    public abstract void read(Connection connection, DataInputStream dis);

    protected void write(DataOutputStream dos, Packet<?> packet) {
        try {
            JSONObject data = new JSONObject();
            data.put("channel", getName());
            data.put("data", packet.writeable());

            ByteBuffer buffer = new ByteBuffer(data.toString().getBytes());
            //dos.writeUTF(name);
            //dos.write(buffer.getBuffer().length); -- not needed for now
            dos.write("aaaasafpüsdjhogisfh8i0odfshgiofdsg".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

}
