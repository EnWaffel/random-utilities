package de.enwaffel.randomutils.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

class NetConnectionImpl implements NetConnection {

    private final Socket socket;
    private final List<Integer> allowedChannels = new ArrayList<>();

    protected NetConnectionImpl(Socket socket) {
        this.socket = socket;
    }

    @Override
    public boolean isOpen() {
        return !socket.isClosed();
    }

    @Override
    public InputStream getInput() {
        try {
            return socket.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OutputStream getOutput() {
        try {
            return socket.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void allow(NetChannel channel) {
        allowedChannels.add(channel.getId());
    }

    @Override
    public void disallow(NetChannel channel) {
        allowedChannels.remove(channel.getId());
    }

    @Override
    public List<Integer> getAllowChannels() {
        return allowedChannels;
    }

    @Override
    public InetAddress getAddress() {
        return socket.getInetAddress();
    }

    @Override
    public InetAddress getLocalAddress() {
        return socket.getLocalAddress();
    }

    @Override
    public SocketAddress getSocketAddress() {
        return socket.getLocalSocketAddress();
    }

    @Override
    public SocketAddress getRemoteSocketAddress() {
        return socket.getRemoteSocketAddress();
    }

}
