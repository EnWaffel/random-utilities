package de.enwaffel.randomutils.sql;

import de.enwaffel.randomutils.Properties;
import de.enwaffel.randomutils.callback.Callback;

public class SQLUpdateTask extends SQLTask {

    private final String table;
    private final SQLEntry entry;
    private final String anyLabel;
    private final Object anyValue;

    protected SQLUpdateTask(SQL sql, String table, SQLEntry entry, String anyLabel, Object anyValue) {
        super(sql);
        this.table = table;
        this.entry = entry;
        this.anyLabel = anyLabel;
        this.anyValue = anyValue;
    }

    @Override
    public boolean completeCheck() {
        if (cancelled) return false;
        return execute();
    }

    @Override
    public SQLTask complete() {
        if (cancelled) return null;
        execute();
        return this;
    }

    @Override
    public void completeAsync(Callback callback) {
        if (cancelled) return;
        asyncCallback = callback;
        execute();
    }

    private boolean execute() {
        try {
            if (cancelled) return false;

            boolean result = sql.update(this, table, entry, anyLabel, anyValue);

            if (asyncCallback != null) asyncCallback.call("complete", new Properties().set("result", result));
            return result;
        } catch (Exception e) {
            System.err.println("SQL Error: Failed to update in database. (" + e.getMessage() + ")");
            return false;
        }
    }

}
