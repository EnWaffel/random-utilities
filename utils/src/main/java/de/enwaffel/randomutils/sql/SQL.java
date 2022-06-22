package de.enwaffel.randomutils.sql;

public abstract class SQL {

    private SQLDriver driver;

    public SQL(SQLDriver driver) {
        this.driver = driver;
    }

    /**
     * Forces the data {@code entry} onto the database
     * (Like GitHub where the data can also be pushed)
     * @param table The table the data is sent to.
     * @param entry The actual data.
     * @return {@code true} if succeeded, {@code false} if not.
     */
    public abstract boolean push(String table, SQLEntry entry);

    /**
     * Goes through every entry in {@code table} and checks if {@code anyLabel} with {@code anyValue} is present within the entry.
     * When the check succeeds, then all the data from {@code entry} is updated.
     * (Like GitHub where the data can also be committed)
     * @param table The table the data is sent to.
     * @param entry The actual data.
     * @param anyLabel The labels that should be checked.
     * @param anyValue The data the checked labels should have.
     * @return {@code true} if succeeded, {@code false} if not.
     */
    public abstract boolean commit(String table, SQLEntry entry, String anyLabel, Object anyValue);

    /**
     * Checks if {@code label} with the data {@code value} is present.
     * @param table The table where the data should be checked.
     * @param label The label that should be checked.
     * @param value The value the checked label should have.
     * @return {@code true} if the label with the same data is present, {@code false} if not.
     */
    public abstract boolean has(String table, String label, Object value);

    /**
     * Goes through every entry in {@code table} and checks if {@code anyLabels} with {@code anyValues} are present within the entry.
     * When the check succeeds, then the data is read from the entry and converted to the {@code entries} classes.
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

    /**
     * Connects to the provided address.
     * @param t The SQL-Type class. (Example: MySQL.class)
     * @param parameters The required parameters for the sql type
     * @return A new SQL object connected to the provided address. Or null if the connection-process failed.
     */
    public SQL connect(Class<? extends SQL> t, Object... parameters) {
        try {
            Class<?>[] classParameters = new Class<?>[parameters.length];
            for (int i = 0;i < parameters.length;i++) {
                classParameters[i] = parameters[i].getClass();
            }
            return t.getDeclaredConstructor(classParameters).newInstance(parameters);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
