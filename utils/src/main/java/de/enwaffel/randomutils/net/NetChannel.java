package de.enwaffel.randomutils.net;

public abstract class NetChannel {
    public abstract void send(NetConnection connection, Packet<?> packet);
    public abstract void send(NetConnection connection, Packet<?>... packets);
    public abstract void send(Packet<?> packet);
    public abstract void send(Packet<?>... packets);
    public abstract void listen(ChannelInputListener listener);
    public abstract void unlisten(ChannelInputListener listener);
    public abstract int getId();
    protected abstract void readInput(ChannelHolder holder, NetConnection connection);
}
