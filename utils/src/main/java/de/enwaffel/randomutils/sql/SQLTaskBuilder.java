package de.enwaffel.randomutils.sql;

public interface SQLTaskBuilder {
    SQLSetTask newSetTask(String table, SQLEntry entry);
    SQLUpdateTask newUpdateTask(String table, SQLEntry entry, String anyLabel, Object anyValue);
    SQLHasTask newHasTask(String table, String label, Object value);
    SQLGetTask newGetTask(String table, PullEntries entries, String[] anyLabels, Object... anyValues);
    SQLCreateTableTask newCreateTableTask(String table, String[] columns, SQLDataType... dataTypes);
    SQLCreateTableTask newCreateTableTask(String table, String[] columns, String[] extraData, SQLDataType... dataTypes);
    SQLDeleteTask newDeleteTask(String table, String label, String value);
    SQLClearTableTask newClearTableTask(String table);
    SQLDropTableTask newDropTableTask(String table);
}
