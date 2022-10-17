package de.enwaffel.randomutils.sql;

import de.enwaffel.randomutils.Properties;
import de.enwaffel.randomutils.callback.Callback;

public class SQLClearTableTask extends SQLTask {

    private final String table;

    protected SQLClearTableTask(SQL sql, String table) {
        super(sql);
        this.table = table;
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

            boolean result = sql.clearTable(this, table);

            if (asyncCallback != null) asyncCallback.call("complete", new Properties().set("result", result));
            return result;
        } catch (Exception e) {
            System.err.println("SQL Error: Failed to check in database. (" + e.getMessage() + ")");
            e.printStackTrace();
            return false;
        }
    }

}
