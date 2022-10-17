package de.enwaffel.randomutils.sql;

import de.enwaffel.randomutils.Properties;
import de.enwaffel.randomutils.callback.Callback;

public class SQLCreateTableTask extends SQLTask {

    private final String table;
    private final String[] columns;
    private String[] extraData = new String[]{};
    private final SQLDataType[] dataTypes;

    protected SQLCreateTableTask(SQL sql, String table, String[] columns, String[] extraData, SQLDataType... dataTypes) {
        super(sql);
        this.table = table;
        this.columns = columns;
        this.extraData = extraData;
        this.dataTypes = dataTypes;
    }

    protected SQLCreateTableTask(SQL sql, String table, String[] columns, SQLDataType... dataTypes) {
        super(sql);
        this.table = table;
        this.columns = columns;
        this.dataTypes = dataTypes;
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

            boolean result = sql.createTable(this, table, columns, extraData, dataTypes);

            if (asyncCallback != null) asyncCallback.call("complete", new Properties().set("result", result));
            return result;
        } catch (Exception e) {
            System.err.println("SQL Error: Failed to create table in database. (" + e.getMessage() + ")");
            e.printStackTrace();
            return false;
        }
    }

}
