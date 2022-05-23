package de.enwaffel.randomutils.channel;

import de.enwaffel.randomutils.buff.InByteBuffer;
import de.enwaffel.randomutils.client.Connection;

import java.io.DataInputStream;
import java.util.Arrays;

public class ClientChannel extends Channel {

    public ClientChannel(String name) {
        super(name);
    }

    @Override
    public void read(Connection connection, DataInputStream dis) {
        InByteBuffer dataBuff = new InByteBuffer(dis);
        System.out.println(Arrays.toString(dataBuff.readFully())+ "AA");
    }

}
