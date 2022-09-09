package de.gml;

import de.enwaffel.randomutils.Properties;

public interface ServiceBase extends Basic {
    void init(Properties properties);
    void update(float delta);
    void disable();
}
