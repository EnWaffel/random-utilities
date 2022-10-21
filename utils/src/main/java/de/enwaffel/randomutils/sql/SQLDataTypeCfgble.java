package de.enwaffel.randomutils.sql;

enum SQLDataTypeCfgble {
    // string
    CHAR(true),
    VARCHAR(true),
    TINYTEXT(true),
    TEXT(true),
    MEDIUMTEXT(true),
    LONGTEXT(true),
    BINARY(true),
    VARBINARY(true),
    // number
    BIT(false),
    TINYINT(true),
    SMALLINT(true),
    MEDIUMINT(true),
    INT(true),
    INTEGER(true),
    BIGINT(true),
    DECIMAL(true),
    DEC(true),
    NUMERIC(true),
    FIXED(true),
    FLOAT(true),
    DOUBLE(true),
    DOUBLEPRECISION(true),
    REAL(true),
    BOOL(false),
    BOOLEAN(false),
    // date / time
    DATE(false),
    DATETIME(false),
    TIMESTAMP(true),
    TIME(false),
    YEAR(true),
    // LOB / BLOB
    TINYBLOB(false),
    BLOB(true),
    MEDIUMBLOB(false),
    LONGBLOB(false),
    ENUM(true),
    SET(true),
    // geometry
    GEOMETRY(true),
    POINT(true),
    LINESTRING(true),
    POLYGON(true),
    MULTIPOINT(true),
    MULTILINESTRING(true),
    MULTIPOLYGON(true),
    GEOMETRYCOLLECTION(true),
    // json
    JSON(false),

    NONE(false);

    private final boolean cfgable;

    SQLDataTypeCfgble(boolean cfgable) {
        this.cfgable = cfgable;
    }

    public boolean get() {
        return cfgable;
    }

    public static SQLDataTypeCfgble get(String name) {
        for (SQLDataTypeCfgble dataTypeCfgble : values()) {
            if (dataTypeCfgble.name().equals(name)) {
                return dataTypeCfgble;
            }
        }
        return SQLDataTypeCfgble.NONE;
    }

    public static boolean get(SQLDataType dataType) {
        return get(dataType.name()).get();
    }

}
