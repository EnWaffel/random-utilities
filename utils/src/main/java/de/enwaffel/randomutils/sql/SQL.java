package de.enwaffel.randomutils.sql;

public abstract class SQL {

    private SQLDriver driver;

    public SQL(SQLDriver driver) {
        this.driver = driver;
    }

    /**
     *
     * @param table The table the data is sent to.
     * @param entry The actual data.
     * @return {@code true} if succeeded, {@code false} if not.
     */
    public abstract boolean push(String table, SQLEntry entry);

    /**
     *
     * @param table The table the data is sent to.
     * @param entry The actual data.
     * @param anyLabel The labels that should be checked.
     * @param anyValue The data the checked labels should have.
     * @return {@code true} if succeeded, {@code false} if not.
     */
    public abstract boolean commit(String table, SQLEntry entry, String anyLabel, Object anyValue);

    /**
     * @param table The table where the data should be checked.
     * @param label The label that should be checked.
     * @param value The value the checked label should have.
     * @return {@code true} if the label with the same data is present, {@code false} if not.
     */
    public abstract boolean has(String table, String label, Object value);

    /**
     * @param table The table the data is retrieved from.
     * @param entries The classes from the objects that should be retrieved.
     * @param anyLabels The labels that should be checked.
     * @param anyValues The data the checked labels should have.
     * @return The retrieved data.
     */
    public abstract SQLEntry pull(String table, PullEntries entries, String[] anyLabels, Object... anyValues);

    public SQLDriver getDriver() {
        return driver;
    }

}
