package de.enwaffel.randomutils.sql;

import de.enwaffel.randomutils.Properties;
import de.enwaffel.randomutils.callback.Callback;

public class SQLGetTask extends SQLTask {

    private final String table;
    private final PullEntries entries;
    private final String[] anyLabels;
    private final Object[] anyValues;

    protected SQLGetTask(SQL sql, String table, PullEntries entries, String[] anyLabels, Object... anyValues) {
        super(sql);
        this.table = table;
        this.entries = entries;
        this.anyLabels = anyLabels;
        this.anyValues = anyValues;
    }

    @Override
    public void cancel() {
        cancelled = true;
    }

    @Override
    public boolean completeCheck() {
        throw new RuntimeException(new IllegalAccessException("This method is not usable in the SQLGetTask class"));
    }

    @Override
    public SQLTask complete() {
        throw new RuntimeException(new IllegalAccessException("This method is not usable in the SQLGetTask class"));
    }

    @Override
    public void completeAsync(Callback callback) {
        if (cancelled) return;
        asyncCallback = callback;
        execute();
    }

    private void execute() {
        try {
            if (cancelled) return;

            SQLEntry result = sql.get(this, table, entries, anyLabels, anyValues);

            if (asyncCallback != null) asyncCallback.call("complete", new Properties().set("result", result));
        } catch (Exception e) {
            System.err.println("SQL Error: Failed to check in database. (" + e.getMessage() + ")");
        }
    }

}
