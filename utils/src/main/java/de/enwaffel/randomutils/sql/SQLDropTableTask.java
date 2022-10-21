package de.enwaffel.randomutils.sql;

import de.enwaffel.randomutils.Properties;
import de.enwaffel.randomutils.callback.Callback;

public class SQLDropTableTask extends SQLTask {

    private final String table;

    protected SQLDropTableTask(SQL sql, String table) {
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

            boolean result = sql.dropTable(this, table);

            if (asyncCallback != null) asyncCallback.call("complete", new Properties().set("result", result));
            return result;
        } catch (Exception e) {
            System.err.println("SQL Error: Failed to drop table in database. (" + e.getMessage() + ")");
            e.printStackTrace();
            return false;
        }
    }

}
