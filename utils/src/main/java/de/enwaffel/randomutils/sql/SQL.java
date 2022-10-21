package de.enwaffel.randomutils.sql;

import java.sql.SQLException;
import java.util.HashMap;

public abstract class SQL implements SQLTaskBuilder {

    private static final HashMap<Class<? extends SQLCodeBuilder>, SQLCodeBuilder> builders = new HashMap<>();
    protected boolean showGeneratedCode;

    static {
        builders.put(DefaultSQLCodeBuilder.class, new DefaultSQLCodeBuilder());
    }

    protected SQL() {
    }

    /**
     * Forces the data {@code entry} into the database
     * @param table The table the data is sent to.
     * @param entry The actual data.
     * @return {@code true} if succeeded, {@code false} if not.
     */
    protected abstract boolean set(SQLTask task, String table, SQLEntry entry) throws SQLException;

    /**
     * Goes through every entry in {@code table} and checks if {@code anyLabel} with {@code anyValue} is present within the entry.
     * When the check succeeds, then all the data from {@code entry} is updated.
     * @param table The table the data is sent to.
     * @param entry The actual data.
     * @param anyLabel The labels that should be checked.
     * @param anyValue The data the checked labels should have.
     * @return {@code true} if succeeded, {@code false} if not.
     */
    protected abstract boolean update(SQLTask task, String table, SQLEntry entry, String anyLabel, Object anyValue) throws SQLException;

    /**
     * Checks if {@code label} with the data {@code value} is present.
     * @param table The table where the data should be checked.
     * @param label The label that should be checked.
     * @param value The value the checked label should have.
     * @return {@code true} if the label with the same data is present, {@code false} if not.
     */
    protected abstract boolean has(SQLTask task, String table, String label, Object value) throws SQLException;

    /**
     * Goes through every entry in {@code table} and checks if {@code anyLabels} with {@code anyValues} are present within the entry.
     * When the check succeeds, then the data is read from the entry and converted to the {@code entries} classes.
     * @param table The table the data is retrieved from.
     * @param entries The classes from the objects that should be retrieved.
     * @param anyLabels The labels that should be checked.
     * @param anyValues The data the checked labels should have.
     * @return The retrieved data.
     */
    protected abstract SQLEntry get(SQLTask task, String table, PullEntries entries, String[] anyLabels, Object... anyValues) throws SQLException;

    protected abstract boolean createTable(SQLTask task, String table, String[] columns, String[] extraData, SQLDataType... dataTypes) throws SQLException;

    protected abstract boolean delete(SQLTask task, String table, String label, Object value) throws SQLException;

    protected abstract boolean clearTable(SQLTask task, String table) throws SQLException;

    protected abstract boolean dropTable(SQLTask task, String table) throws SQLException;

    @Override
    public SQLSetTask newSetTask(String table, SQLEntry entry) {
        return new SQLSetTask(this, table, entry);
    }

    @Override
    public SQLUpdateTask newUpdateTask(String table, SQLEntry entry, String anyLabel, Object anyValue) {
        return new SQLUpdateTask(this, table, entry, anyLabel, anyValue);
    }

    @Override
    public SQLHasTask newHasTask(String table, String label, Object value) {
        return new SQLHasTask(this, table, label, value);
    }

    @Override
    public SQLGetTask newGetTask(String table, PullEntries entries, String[] anyLabels, Object... anyValues) {
        return new SQLGetTask(this, table, entries, anyLabels, anyValues);
    }

    @Override
    public SQLCreateTableTask newCreateTableTask(String table, String[] columns, SQLDataType... dataTypes) {
        return new SQLCreateTableTask(this, table, columns, dataTypes);
    }

    @Override
    public SQLCreateTableTask newCreateTableTask(String table, String[] columns, String[] extraData, SQLDataType... dataTypes) {
        return new SQLCreateTableTask(this, table, columns, extraData, dataTypes);
    }

    @Override
    public SQLDeleteTask newDeleteTask(String table, String label, String value) {
        return new SQLDeleteTask(this, table, label, value);
    }

    @Override
    public SQLClearTableTask newClearTableTask(String table) {
        return new SQLClearTableTask(this, table);
    }

    @Override
    public SQLDropTableTask newDropTableTask(String table) {
        return new SQLDropTableTask(this, table);
    }

    public void showGeneratedCode(boolean showGeneratedCode) {
        this.showGeneratedCode = showGeneratedCode;
    }

    /**
     * Connects to the provided address.
     * @param t The SQL-Type class. (Example: MySQL.class)
     * @param parameters The required parameters for the sql type
     * @return A new SQL object connected to the provided address. Or null if the connection-process failed.
     */
    public static <T extends SQL> T connect(Class<? extends SQL> t, Object... parameters) {
        try {
            Class<?>[] classParameters = new Class<?>[parameters.length];
            for (int i = 0;i < parameters.length;i++) {
                classParameters[i] = parameters[i].getClass();
            }
            return (T) t.getDeclaredConstructor(classParameters).newInstance(parameters);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    public static SQLCodeBuilder getDefaultCodeBuilder() {
        return builders.get(DefaultSQLCodeBuilder.class);
    }

}
