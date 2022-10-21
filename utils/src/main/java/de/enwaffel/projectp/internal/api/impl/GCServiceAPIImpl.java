package de.enwaffel.projectp.internal.api.impl;

import de.enwaffel.projectp.api.guardiencraft.GCPlayer;
import de.enwaffel.projectp.api.guardiencraft.GCServiceAPI;
import de.enwaffel.projectp.exception.APICommunicationException;
import de.enwaffel.projectp.exception.GCException;
import de.enwaffel.projectp.exception.NotRegisteredException;
import de.enwaffel.randomutils.io.ByteBuffer;
import de.enwaffel.randomutils.net.*;
import org.json.JSONObject;
import sun.misc.GC;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class GCServiceAPIImpl implements GCServiceAPI {

    private final NetClient client = new NetClient();
    private final NetChannel apiChannel;

    public GCServiceAPIImpl() {
        client.connect("gamersucht.net", 25590); // connect to the guardiencraft service server
        apiChannel = client.createChannel(99);
    }

    @Override
    public GCPlayer getGCPlayer(String mcName) throws GCException {
        return _getGCPlayer(mcName);
    }

    @Override
    public GCPlayer getGCPlayer(UUID mcUUID) throws GCException {
        return _getGCPlayer(mcUUID);
    }

    @Override
    public GCPlayer getGCPlayer(long dcID) throws GCException {
        return _getGCPlayer(dcID);
    }

    // private methods

    private GCPlayer _getGCPlayer(Object value) throws GCException {
        AtomicReference<JSONObject> _json = new AtomicReference<>(null);
        AtomicBoolean await = new AtomicBoolean();
        ChannelInputListener listener = new ChannelInputListener() {
            @Override
            public void onPacketReceived(NetConnection connection, Packet<?> packet) {
                _json.set(new JSONObject());
            }
            @Override
            public void onPacketReceived(NetConnection connection, ByteBuffer buffer) {}
        };
        apiChannel.listen(listener);
        while (!await.get()) _wait();
        apiChannel.unlisten(listener);
        if (_json.get() == null) throw new APICommunicationException("Got invalid response from api server. (NULL)");
        JSONObject json = _json.get();
        return null;
    }

    private void _wait() {
    }

}
