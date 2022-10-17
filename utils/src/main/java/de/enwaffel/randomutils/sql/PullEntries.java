package de.enwaffel.randomutils.sql;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class PullEntries {

    private final LinkedHashMap<String, Class<?>> entries = new LinkedHashMap<>();

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
