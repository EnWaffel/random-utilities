package de.enwaffel.randomutils;

import java.util.HashMap;

public class Properties {

    private final HashMap<String, Property> properties = new HashMap<>();

    public Properties set(String key, Property property) {
        properties.put(key, property);
        return this;
    }

    public Property get(String key) {
        return properties.getOrDefault(key, new Property(null));
    }

    public Properties remove(String key) {
        properties.remove(key);
        return this;
    }

}
