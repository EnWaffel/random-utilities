package de.enwaffel.randomutils.sql;

public class SQLUtil {

    private SQLUtil() {
    }

    protected static boolean checkTableName(String table) {
        return !table.contains("-") && !table.contains(" ") && !table.contains(".")
                && !table.contains(":") && !table.contains(",") && !table.contains(";")
                && !table.contains("?") && !table.contains("=") && !table.contains(">")
                && !table.contains("<") && !table.contains("^") && !table.contains("°")
                && !table.contains("!") && !table.contains("\"") && !table.contains("'")
                && !table.contains("´") && !table.contains("`") && !table.contains("(")
                && !table.contains(")") && !table.contains("[") && !table.contains("]")
                && !table.contains("{") && !table.contains("}") && !table.contains("*")
                && !table.contains("/") && !table.contains("\\") && !table.contains("§")
                && !table.contains("$") && !table.contains("%") && !table.contains("&");
    }

    protected static Object replaceSpaces(Object str) {
        if (str.getClass().equals(String.class)) {
            if (((String) str).contains(" ")) {
                return "'" + str + "'";
            }
            return str;
        }
        return str;
    }

}
