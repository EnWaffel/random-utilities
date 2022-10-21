package de.enwaffel.projectp.api.guardiencraft;

import java.util.UUID;

public interface GCPlayer {
    GCDiscordStats getDiscordStats();
    String getMCName();
    UUID getMCUUID();
}
