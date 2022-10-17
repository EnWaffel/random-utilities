package de.enwaffel.projectp.api.service;

import de.enwaffel.randomutils.io.ByteBuffer;

public interface AssetProvider  {
    ByteBuffer getAsset(String name);
    void setAsset(String location, ByteBuffer data);
}
