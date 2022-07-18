package de.enwaffel.util;

import org.luaj.vm2.LuaValue;

public class Validate {

    public static boolean str(LuaValue v) {
        return errIf(v.isstring(), "not a string");
    }

    public static boolean num(LuaValue v) {
        return errIf(v.isnumber(), "not a number");
    }

    public static boolean i(LuaValue v) {
        try {
            Integer.parseInt(v.tojstring());
            return true;
        } catch (Exception e) {
            return errIf(true, "not a integer");
        }
    }

    public static boolean errIf(boolean v, String m) {
        if (!v) {
            throw new IllegalArgumentException("Invalid argument: " + m);
        } else {
            return true;
        }
    }

    public static void tryCatch(Runnable r) {
        try {
            r.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
