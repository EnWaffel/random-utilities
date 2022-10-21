package de.enwaffel.randomutils.sql;

public enum SQLDataType {
    // string
    CHAR("CHAR"),
    VARCHAR("VARCHAR"),
    TINYTEXT("TINYTEXT"),
    TEXT("TEXT"),
    MEDIUMTEXT("MEDIUMTEXT"),
    LONGTEXT("LONGTEXT"),
    BINARY("BINARY"),
    VARBINARY("VARBINARY"),
    // number
    BIT("BIT"),
    TINYINT("TINYINT"),
    SMALLINT("SMALLINT"),
    MEDIUMINT("MEDIUMINT"),
    INT("INT"),
    INTEGER("INTEGER"),
    BIGINT("BIGINT"),
    DECIMAL("DECIMAL"),
    DEC("DEC"),
    NUMERIC("NUMERIC"),
    FIXED("FIXED"),
    FLOAT("FLOAT"),
    DOUBLE("DOUBLE"),
    DOUBLEPRECISION("DOUBLEPRECISION"),
    REAL("REAL"),
    BOOL("BOOL"),
    BOOLEAN("BOOLEAN"),
    // date / time
    DATE("DATE"),
    DATETIME("DATETIME"),
    TIMESTAMP("TIMESTAMP"),
    TIME("TIME"),
    YEAR("YEAR"),
    // LOB / BLOB
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
    JSON("JSON"),

    NONE(null);

    private final String text;

    SQLDataType(String text) {
        this.text = text;
    }

    public String get() {
        return text;
    }

}
