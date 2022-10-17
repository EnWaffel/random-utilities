package de.enwaffel.randomutils.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.List;

public interface NetConnection {
    boolean isOpen();
    InputStream getInput();
    OutputStream getOutput();
    void allow(NetChannel channel);
    void disallow(NetChannel channel);
    List<Integer> getAllowChannels();
    InetAddress getAddress();
    InetAddress getLocalAddress();
    SocketAddress getSocketAddress();
    SocketAddress getRemoteSocketAddress();
}
