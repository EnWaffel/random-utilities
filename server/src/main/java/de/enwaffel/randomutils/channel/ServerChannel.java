package de.enwaffel.randomutils.channel;

import de.enwaffel.randomutils.buff.InByteBuffer;
import de.enwaffel.randomutils.client.Connection;

import java.io.DataInputStream;

public class ServerChannel extends Channel {

    public ServerChannel(String name) {
        super(name);
    }

    @Override
    public void read(Connection connection, DataInputStream dis) {
        InByteBuffer dataBuff = new InByteBuffer(dis);
        dataBuff.readFully();
        System.out.println(new String(dataBuff.getBuffer()));
    }

}
