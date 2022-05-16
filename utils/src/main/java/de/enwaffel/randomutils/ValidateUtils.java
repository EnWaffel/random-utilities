package de.enwaffel.randomutils;

import de.enwaffel.randomutils.file.FileOrPath;

public class ValidateUtils {

    public static boolean fileExists(FileOrPath fileOrPath) {
        return fileOrPath.getFile().exists();
    }

}
