package de.enwaffel.randomutils;

import java.util.HashMap;

public class Properties {

    private final HashMap<String, Property> properties = new HashMap<>();

    public Properties set(String key, Property property) {
        properties.put(key, property);
        return this;
    }

    public Properties set(String key, Object value) {
        properties.put(key, new Property(value));
        return this;
    }

    public Property get(String key) {
        return properties.getOrDefault(key, new Property(null));
    }

    public Properties remove(String key) {
        properties.remove(key);
        return this;
    }

    public boolean has(String key) {
        for (String k : properties.keySet()) {
            if (k.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public HashMap<String, Property> list() {
        return properties;
    }

    @Override
    public String toString() {
        return "Properties{" +
                "properties=" + properties +
                '}';
    }

}
