package de.enwaffel.randomutils.sql;

import de.enwaffel.randomutils.ArrayUtil;

import java.sql.*;
import java.util.Collection;
import java.util.Properties;

/**
 * Parameters: address : {@link String}, database : {@link String}, username : {@link String}, password : {@link String}, driver(optional) : {@code YourMySQLDriver}
 */
public class MySQL extends SQL {

    private Connection connection;

    protected MySQL(String address, String db, String un, String pw) {
        String url = "jdbc:mysql://" + address + "/" + db;
        getDriver("com.mysql.cj.jdbc.Driver");
        java.sql.Driver driver = getDriver("com.mysql.cj.jdbc.Driver");
        Properties info = new Properties();
        info.put("user", un);
        info.put("password", pw);
        try {
            connection = driver.connect(url, info);
        } catch (Exception e) {
            if (e.getMessage().equals("Communications link failure\n" +
                    "\n" +
                    "The last packet sent successfully to the server was 0 milliseconds ago. The driver has not received any packets from the server.")) {
                System.err.println("SQL Error: Failed to connect to database: " + address);
            }
        }
    }

    protected MySQL(String address, String db, String un, String pw, String d) {
        String url = "jdbc:mysql//" + address + "/" + db;
        java.sql.Driver driver = getDriver(d);
        Properties info = new Properties();
        info.put("user", un);
        info.put("password", pw);
        try {
            connection = driver.connect(url, info);
        } catch (Exception e) {
            if (e.getMessage().equals("Communications link failure\n" +
                    "\n" +
                    "The last packet sent successfully to the server was 0 milliseconds ago. The driver has not received any packets from the server.")) {
                System.err.println("SQL Error: Failed to connect to database: " + address);
            }
        }
    }

    private java.sql.Driver getDriver(String d) {
        try {
            Class<?> c = Class.forName(d);
            if (!ArrayUtil.contains(c.getInterfaces(), java.sql.Driver.class)) throw new SQLException("Driver Class is not a valid driver class");
            Class<java.sql.Driver>  driverClass = (Class<Driver>) c;
            if (driverClass.getDeclaredConstructors()[0].getParameters().length > 0) throw new SQLException("Driver Class has too many arguments in the constructor");
            return driverClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected boolean set(SQLTask task, String table, SQLEntry entry) {
        try {
            if (!SQLUtil.checkTableName(table)) throw new SQLException("Invalid table name for: " + table + " (table name can only contain letters and underscores!)");
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
                args.append("'").append(value).append("'");
                if (oi < (values.size() - 1)) {
                    args.append(",");
                }
                oi++;
            }
            args.append(");");
            if (showGeneratedCode) System.out.println("GENERATED SQL CODE: " + args);
            PreparedStatement post = connection.prepareStatement(args.toString());
            post.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected boolean update(SQLTask task, String table, SQLEntry entry, String anyLabel, Object anyValue) {
        try {
            if (!SQLUtil.checkTableName(table)) throw new SQLException("Invalid table name for: " + table + " (table name can only contain letters and underscores!)");
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

            if (showGeneratedCode) System.out.println("GENERATED SQL CODE: " + args);
            PreparedStatement post = connection.prepareStatement(args.toString());
            post.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected boolean has(SQLTask task, String table, String label, Object value) {
        try {
            if (!SQLUtil.checkTableName(table)) throw new SQLException("Invalid table name for: " + table + " (table name can only contain letters and underscores!)");
            String v = value.toString();
            String args = "SELECT '" + label + "' FROM " + table + " WHERE " + label + " = '" + v + "';";

            if (showGeneratedCode) System.out.println("GENERATED SQL CODE: " + args);
            PreparedStatement statement = connection.prepareStatement(args);
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
    protected SQLEntry get(SQLTask task, String table, PullEntries entries, String[] anyLabels, Object... anyValues) {
        try {
            if (!SQLUtil.checkTableName(table)) throw new SQLException("Invalid table name for: " + table + " (table name can only contain letters and underscores!)");
            SQLEntry entry = new SQLEntry();
            StringBuilder args = new StringBuilder("SELECT ");
            String[] names = entries.getEntries().keySet().toArray(new String[]{});
            Class<?>[] classes = entries.getEntries().values().toArray(new Class<?>[]{});

            int li = 0;
            for (String label : names) {
                args.append(label);
                if ((li + 1) != names.length) args.append(", ");
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
            if (showGeneratedCode) System.out.println("GENERATED SQL CODE: " + args);
            PreparedStatement statement = connection.prepareStatement(args.toString());
            ResultSet result = statement.executeQuery();

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

    @Override
    protected boolean createTable(SQLTask task, String table, String[] columns, String[] extraData, SQLDataType... dataTypes) {
        try {
            if (!SQLUtil.checkTableName(table)) throw new SQLException("Invalid table name for: " + table + " (table name can only contain letters and underscores!)");
            StringBuilder args = new StringBuilder();
            args.append("CREATE TABLE ").append(table).append(" (");

            int i = 0;
            for (String str : columns) {
                args.append(str).append(" ").append(dataTypes[i].get());
                if (extraData.length >= (i + 1)) if (extraData[i] != null) args.append("(").append(extraData[i]).append(")");
                if ((i + 1) != columns.length) args.append(",");
                i++;
            }

            args.append(");");
            if (showGeneratedCode) System.out.println("GENERATED SQL CODE: " + args);
            PreparedStatement post = connection.prepareStatement(args.toString());
            post.execute();

            return true;
        } catch (Exception e) {
            if (!e.getMessage().contains("already exists")) {
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    protected boolean delete(SQLTask task, String table, String label, Object value) {
        try {
            if (!SQLUtil.checkTableName(table)) throw new SQLException("Invalid table name for: " + table + " (table name can only contain letters and underscores!)");

            String args = "DELETE FROM " + table + " WHERE " +
                    label + " = '" + value +
                    "'";

            if (showGeneratedCode) System.out.println("GENERATED SQL CODE: " + args);
            PreparedStatement post = connection.prepareStatement(args);
            post.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected boolean clearTable(SQLTask task, String table) {
        try {
            if (!SQLUtil.checkTableName(table)) throw new SQLException("Invalid table name for: " + table + " (table name can only contain letters and underscores!)");

            String args = "TRUNCATE TABLE " + table + ";";

            if (showGeneratedCode) System.out.println("GENERATED SQL CODE: " + args);
            PreparedStatement post = connection.prepareStatement(args);
            post.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected boolean dropTable(SQLTask task, String table) {
        try {
            if (!SQLUtil.checkTableName(table)) throw new SQLException("Invalid table name for: " + table + " (table name can only contain letters and underscores!)");

            String args = "DROP TABLE " + table + ";";

            if (showGeneratedCode) System.out.println("GENERATED SQL CODE: " + args);
            PreparedStatement post = connection.prepareStatement(args);
            post.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
