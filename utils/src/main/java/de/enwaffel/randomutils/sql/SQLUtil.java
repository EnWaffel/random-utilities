package de.enwaffel.randomutils.sql;

class SQLUtil {

    private SQLUtil() {
    }

    public static boolean checkTableName(String table) {
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

    public static Object replaceSpaces(Object str) {
        if (str.getClass().equals(String.class)) {
            if (((String) str).contains(" ")) {
                return "'" + str + "'";
            }
            return str;
        }
        return str;
    }

    public static String formatLabel(String label, String extraData, SQLDataType dataType) {
        StringBuilder builder = new StringBuilder();
        builder.append(label);
        if (SQLDataTypeCfgble.get(dataType) && extraData != null) {
            if (!dataType.equals(SQLDataType.YEAR)) {
                builder.append("(").append(extraData).append(")");
            } else {
                builder.append("[(").append(extraData).append(")]");
            }
        }
        return builder.toString();
    }

}
