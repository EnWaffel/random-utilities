package de.enwaffel.randomutils.sql;

import org.apache.commons.lang3.ArrayUtils;

public class SQLStatement {

    protected String name = "";
    protected String[] args = new String[]{};

    public SQLStatement name(String name) {
        this.name = name;
        return this;
    }

    public SQLStatement arg(String arg) {
        ArrayUtils.add(args, arg);
        return this;
    }

    public SQLStatement get() {
        return this;
    }

}
