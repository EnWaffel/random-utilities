package de.enwaffel.randomutils.sql;

public enum SQLDataType {
    // numeric
    TINYINT("TINYINT"),
    SMALLINT("SMALLINT"),
    MEDIUMINT("MEDIUMINT"),
    INT("INT"),
    BIGINT("BIGINT"),
    DECIMAL("DECIMAL"),
    FLOAT("FLOAT"),
    DOUBLE("DOUBLE"),
    REAL("REAL"),
    BIT("BIT"),
    BOOLEAN("BOOLEAN"),
    SERIAL("SERIAL"),
    // date
    DATE("DATE"),
    DATETIME("DATETIME"),
    TIMESTAMP("TIMESTAMP"),
    TIME("TIME"),
    YEAR("YEAR"),
    // string
    CHAR("CHAR"), // maximum: 255
    VARCHAR("VARCHAR"), // maximum: 255
    TINYTEXT("TINYTEXT"), // maximum: 255
    TEXT("TEXT"), // maximum: 65,535
    MEDIUMTEXT("MEDIUMTEXT"), // maximum: 16,777,215
    LONGTEXT("LONGTEXT"), // maximum: 4,294,967,295
    BINARY("BINARY"),// maximum: 255
    VARBINARY("VARBINARY"),// maximum: 255
    TINYBLOB("TINYBLOB"),
    BLOB("BLOB"),
    MEDIUMBLOB("MEDIUMBLOB"),
    LONGBLOB("LONGBLOB"),
    ENUM("ENUM"),
    SET("SET"),
    // geometry
    GEOMETRY("GEOMETRY"),
    POINT("POINT"),
    LINESTRING("LINESTRING"),
    POLYGON("POLYGON"),
    MULTIPOINT("MULTIPOINT"),
    MULTILINESTRING("MULTILINESTRING"),
    MULTIPOLYGON("MULTIPOLYGON"),
    GEOMETRYCOLLECTION("GEOMETRYCOLLECTION"),
    // json
    JSON("JSON");

    private final String text;

    SQLDataType(String text) {
        this.text = text;
    }

    public String get() {
        return text;
    }

}
