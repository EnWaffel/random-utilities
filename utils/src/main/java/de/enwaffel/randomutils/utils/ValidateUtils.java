package de.enwaffel.randomutils.utils;

public class ValidateUtils {

    public static boolean fileExists(FileOrPath fileOrPath) {
        return fileOrPath.getFile().exists();
    }

}
