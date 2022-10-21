package de.enwaffel.projectp.api.guardiencraft;

import de.enwaffel.projectp.IAPI;
import de.enwaffel.projectp.exception.GCException;

import java.util.UUID;

public interface GCServiceAPI extends IAPI {
    GCPlayer getGCPlayer(String mcName) throws GCException;
    GCPlayer getGCPlayer(UUID mcUUID) throws GCException;
    GCPlayer getGCPlayer(long dcID) throws GCException;
}
