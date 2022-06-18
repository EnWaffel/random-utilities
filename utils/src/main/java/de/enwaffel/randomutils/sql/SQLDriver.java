package de.enwaffel.randomutils.sql;

public class SQLDriver {

    private final Class<?> driverClass;

    protected SQLDriver(Class<?> driverClass) {
        this.driverClass = driverClass;
    }

    public Class<?> getDriverClass() {
        return driverClass;
    }

    public static SQLDriver forClassPath(String classPath) {
        try {
            return new SQLDriver(Class.forName(classPath));
        } catch (Exception e) {
            return null;
        }
    }

    public static SQLDriver default_mysql() {
        return forClassPath("com.mysql.jdbc.Driver");
    }

}
