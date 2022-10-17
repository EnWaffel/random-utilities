package de.enwaffel.randomutils.sql;

import jdk.jfr.Experimental;

@Experimental
public interface SQLCodeBuilder {
    SQLCodeBuilder newBuilder();

    // code
    SQLCodeBuilder statement(SQLStatement statement);
}
