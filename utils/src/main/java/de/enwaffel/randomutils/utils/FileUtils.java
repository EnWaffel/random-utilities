package de.enwaffel.randomutils.utils;

import java.io.FileInputStream;
import java.nio.file.Files;

public class FileUtils {

    // read

    public String readFile(FileOrPath fileOrPath, int maxBytes) {
        try {
            FileInputStream fis = new FileInputStream(fileOrPath.getFile());
            byte[] buffer = new byte[maxBytes];
            while (fis.read(buffer, 0, maxBytes) >= 0);
            return new String(buffer);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String readFile(FileOrPath fileOrPath) {
        try {
            return new String(Files.readAllBytes(fileOrPath.getFile().toPath()));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    // write

    public void writeFile(FileOrPath fileOrPath) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
