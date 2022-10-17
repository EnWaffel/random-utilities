package de.enwaffel.randomutils.net;

import java.util.List;

public interface OutputProvider {
    NetConnection getConnection();
    List<NetConnection> getConnections();
}
