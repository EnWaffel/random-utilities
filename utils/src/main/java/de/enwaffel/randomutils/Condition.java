package de.enwaffel.randomutils;

import de.enwaffel.randomutils.file.FileOrPath;

public enum Condition {
    FILE_NOT_EXIST;

    public static boolean met(Condition condition, Object[] o) {
        switch (condition) {
            case FILE_NOT_EXIST: return ((FileOrPath) o[0]).getFile().exists();
            default: return false;
        }
    }

    public static boolean met(Condition[] conditions, Object... objects) {
        boolean met = false;
        for (Condition condition : conditions) {
            met = met(condition, objects);
        }
        return met;
    }

}
