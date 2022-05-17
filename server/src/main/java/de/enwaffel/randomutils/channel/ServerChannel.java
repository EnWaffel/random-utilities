package de.enwaffel.randomutils.channel;

import de.enwaffel.randomutils.buff.ByteBuffer;
import de.enwaffel.randomutils.buff.InByteBuffer;
import de.enwaffel.randomutils.client.Connection;
import de.enwaffel.randomutils.packet.Packet;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.nio.charset.StandardCharsets;

public class ServerChannel extends Channel {

    public ServerChannel(String name) {
        super(name);
    }

    @Override
    public void read(Connection connection, DataInputStream dis) {
        InByteBuffer dataBuff = new InByteBuffer(dis);
        dataBuff.readFully();
    }

    @Override
    public void write(DataOutputStream dos, Packet<?> packet) {
        try {
            JSONObject data = new JSONObject();
            data.put("channel", getName());
            data.put("data", packet.writeable());

            ByteBuffer buffer = new ByteBuffer(data.toString().getBytes(StandardCharsets.UTF_8));
            dos.writeUTF(name);
            //dos.write(buffer.getBuffer().length); -- not needed for now
            dos.write(buffer.getBuffer());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
