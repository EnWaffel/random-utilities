package de.enwaffel.randomutils.sql.mysql;

import de.enwaffel.randomutils.sql.PullEntries;
import de.enwaffel.randomutils.sql.SQL;
import de.enwaffel.randomutils.sql.SQLDriver;
import de.enwaffel.randomutils.sql.SQLEntry;

import java.sql.*;
import java.util.Collection;

/**
 * Parameters: driver : {@link SQLDriver}, address : {@link String}, database : {@link String}, username : {@link String}, password : {@link String}
 */
public class MySQL extends SQL {

    private final Connection connection;

    protected MySQL(SQLDriver driver, String address, String db, String un, String pw) throws SQLException {
        super(driver);
        String url = "jdbc:mysql://" + address + "/" + db;
        connection = DriverManager.getConnection(url, un, pw);
    }

    @Override
    public boolean push(String table, SQLEntry entry) {
        try {
            Collection<String> labels = entry.getEntries().keySet();
            Collection<Object> values = entry.getEntries().values();

            StringBuilder args = new StringBuilder();
            args.append("INSERT INTO ");
            args.append(table);
            args.append(" (");

            int li = 0;
            for (String label : labels) {
                args.append(" ").append(label);
                if (li < (labels.size() - 1)) {
                    args.append(",");
                }
                li++;
            }
            args.append(") VALUES (");

            int oi = 0;
            for (Object value : values) {
                args.append(" '").append(value).append("'");
                if (oi < (values.size() - 1)) {
                    args.append(",");
                }
                oi++;
            }
            args.append(");");

            PreparedStatement post = connection.prepareStatement(args.toString());
            post.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean commit(String table, SQLEntry entry, String anyLabel, Object anyValue) {
        try {
            Collection<String> labels = entry.getEntries().keySet();
            Object[] values = entry.getEntries().values().toArray();

            StringBuilder args = new StringBuilder();
            args.append("UPDATE ");
            args.append(table);
            args.append(" SET");

            int i = 0;
            for (String label : labels) {
                args.append(" ").append(label).append(" = ").append("'").append(values[i]).append("'");
                if (i < (labels.size() - 1)) {
                    args.append(",");
                }
                i++;
            }
            args.append(" WHERE ").append(anyLabel).append(" = ").append("'").append(anyValue).append("'");
            args.append(";");


            PreparedStatement post = connection.prepareStatement(args.toString());
            post.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean has(String table, String label, Object value) {
        try {
            String v = value.toString();
            PreparedStatement statement = connection.prepareStatement("SELECT '" + label + "' FROM " + table + " WHERE " + label + " = '" + v + "';");
            ResultSet result = statement.executeQuery();
            int i = 0;
            while (result.next()) {
                i++;
            }

            return i > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public SQLEntry pull(String table, PullEntries entries, String[] anyLabels, Object... anyValues) {
        try {
            SQLEntry entry = new SQLEntry();
            StringBuilder args = new StringBuilder("SELECT ");

            int li = 0;
            for (String label : anyLabels) {
                args.append(label);
                if (li < (anyLabels.length - 1)) {
                    args.append(",");
                }
                li++;
            }
            args.append(" FROM ").append(table).append(" WHERE ");

            int oi = 0;
            for (Object value : anyValues) {
                args.append(anyLabels[oi]).append(" ").append("=").append(" ").append("'").append(value).append("'");
                if (oi < (anyValues.length - 2)) {
                    args.append(",");
                }
                oi++;
            }
            args.append(";");

            PreparedStatement statement = connection.prepareStatement(args.toString());
            ResultSet result = statement.executeQuery();

            String[] names = entries.getEntries().keySet().toArray(new String[]{});
            Class<?>[] classes = entries.getEntries().values().toArray(new Class<?>[]{});
            int i = 0;
            while (result.next()) {
                entry.set(names[i], result.getObject(names[i], classes[i]));
                i++;
            }

            return entry;
        } catch (Exception e) {
            e.printStackTrace();
            return new SQLEntry();
        }
    }

}
