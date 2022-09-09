package de.enwaffel.randomutils.nio;

import de.enwaffel.randomutils.io.InByteBuffer;
import de.enwaffel.randomutils.io.OutByteBuffer;

import java.net.InetSocketAddress;

public interface Connection {
    OutByteBuffer getOutputBuffer();
    InByteBuffer getInputBuffer();
    InetSocketAddress getInetAddress();
}
