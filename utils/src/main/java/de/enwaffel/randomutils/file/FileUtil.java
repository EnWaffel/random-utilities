package de.enwaffel.randomutils.file;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;

public class FileUtil {

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

    // json
    public static JSONObject readJSON( FileOrPath fileOrPath) {
        return new JSONObject(readFile(fileOrPath));
    }

    public static JSONObject readJSON(FileOrPath fileOrPath, int buffer) {
        return new JSONObject(readFile(fileOrPath, buffer));
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

}
