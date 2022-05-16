package de.enwaffel.randomutils.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;

public class FileUtils {

    // read

    public static String readFile(FileOrPath fileOrPath, int maxBytes) {
        try {
            FileInputStream fis = new FileInputStream(fileOrPath.getFile());
            byte[] buffer = new byte[maxBytes];
            while (fis.read(buffer, 0, maxBytes) >= 0);
            fis.close();
            return new String(buffer);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String readFile(FileOrPath fileOrPath) {
        try {
            return new String(Files.readAllBytes(fileOrPath.getFile().toPath()));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    // write

    public static void writeFile(Object o, FileOrPath fileOrPath) {
        try {
            FileOutputStream fos = new FileOutputStream(fileOrPath.getFile());
            for (char c : o.toString().toCharArray()) {
                fos.write(c);
            }
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(Object o, FileOrPath fileOrPath, OutputStream os) {
        try {
            if (!(os instanceof FileOutputStream))
                throw new IllegalAccessException("OutputStream must be a FileOutputStream!");
            FileOutputStream fos = (FileOutputStream) os;
            for (char c : o.toString().toCharArray()) {
                fos.write(c);
            }
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // json



}
