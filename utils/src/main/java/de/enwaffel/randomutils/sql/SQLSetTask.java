package de.enwaffel.randomutils.sql;

import de.enwaffel.randomutils.Properties;
import de.enwaffel.randomutils.callback.Callback;

public class SQLSetTask extends SQLTask {

    private final String table;
    private final SQLEntry entry;
    
    protected SQLSetTask(SQL sql, String table, SQLEntry entry) {
        super(sql);
        this.table = table;
        this.entry = entry;
    }

    @Override
    public void cancel() {
        cancelled = true;
    }

    @Override
    public boolean completeCheck() {
        if (cancelled) return false;
        return execute();
    }

    @Override
    public SQLTask complete() {
        if (cancelled) return null;
        complete();
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

            boolean result = sql.set(this, table, entry);

            if (asyncCallback != null) asyncCallback.call("complete", new Properties().set("result", result));
            return result;
        } catch (Exception e) {
            System.err.println("SQL Error: Failed to set in database. (" + e.getMessage() + ")");
            return false;
        }
    }

}
