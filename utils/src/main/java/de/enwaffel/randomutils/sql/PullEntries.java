package de.enwaffel.randomutils.sql;

import java.util.HashMap;

public class PullEntries {

    private final HashMap<String, Class<?>> entries = new HashMap<>();

    public PullEntries set(String name, Class<?> value) {
        entries.put(name, value);
        return this;
    }

    public Class<?> get(String name) {
        return entries.get(name);
    }

    public PullEntries remove(String name) {
        entries.remove(name);
        return this;
    }

    public HashMap<String, Class<?>> getEntries() {
        return entries;
    }

}
