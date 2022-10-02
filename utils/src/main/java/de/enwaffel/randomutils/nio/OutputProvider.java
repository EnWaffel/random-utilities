package de.enwaffel.randomutils.nio;

import java.util.List;

public interface OutputProvider {
    boolean isServer();
    Connection getConnection();
    List<Connection> getConnections();
}
