package de.enwaffel.randomutils.sql;

import de.enwaffel.randomutils.callback.Callback;

public abstract class SQLTask {

    protected SQL sql;
    protected boolean cancelled;
    protected Callback asyncCallback;

    protected SQLTask(SQL sql) {
        this.sql = sql;
    }

    public void cancel() {
        cancelled = true;
    }

    public abstract boolean completeCheck();

    public abstract SQLTask complete();

    public abstract void completeAsync(Callback callback);

}
