package de.enwaffel.randomutils.sql;

import java.util.*;

public class SQLEntry {

    private final LinkedHashMap<String, Object> entries = new LinkedHashMap<>();

    public SQLEntry set(String label, Object value) {
        entries.put(label, value);
        return this;
    }

    public Object get(String label) {
        return entries.get(label);
    }

    public SQLEntry remove(String label) {
        entries.remove(label);
        return this;
    }

    public HashMap<String, Object> getEntries() {
        return entries;
    }

}
