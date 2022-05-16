package de.enwaffel.randomutils.utils;

import de.enwaffel.randomutils.utils.file.FileOrPath;

public class ValidateUtils {

    public static boolean fileExists(FileOrPath fileOrPath) {
        return fileOrPath.getFile().exists();
    }

}
