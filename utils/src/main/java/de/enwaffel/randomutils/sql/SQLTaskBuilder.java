package de.enwaffel.randomutils.sql;

public interface SQLTaskBuilder {
    SQLSetTask newTask(String table, SQLEntry entry);
    SQLUpdateTask newTask(String table, SQLEntry entry, String anyLabel, Object anyValue);
    SQLHasTask newTask(String table, String label, Object value);
    SQLGetTask newTask(String table, PullEntries entries, String[] anyLabels, Object... anyValues);
}
