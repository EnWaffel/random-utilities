package de.enwaffel.randomutils.file;

import de.enwaffel.randomutils.Condition;
import de.enwaffel.randomutils.io.ByteBuffer;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;

/**
 * File writing / reading / etc. utilities.
 */
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
    public static JSONObject readJSON(FileOrPath fileOrPath) {
        return new JSONObject(readFile(fileOrPath));
    }

    public static JSONObject readJSON(FileOrPath fileOrPath, int buffer) {
        return new JSONObject(readFile(fileOrPath, buffer));
    }

    // write
    public static void writeFileIf(Object o, FileOrPath fileOrPath, boolean _if) {
        if (_if) writeFile(o, fileOrPath);
    }

    public static void writeFileIf(Object o, FileOrPath fileOrPath, Condition... conditions) {
        if (Condition.met(conditions, fileOrPath)) writeFile(o, fileOrPath);
    }

    public static void writeFile(Object o, FileOrPath fileOrPath) {
        try {
            FileOutputStream fos = new FileOutputStream(fileOrPath.getFile());
            if (o instanceof ByteBuffer) {
                fos.write(((ByteBuffer) o).getBuffer());
            } else {
                for (char c : o.toString().toCharArray()) {
                    fos.write(c);
                }
            }
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(Object o, OutputStream os) {
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

    public static FileOutputStream getOutputStream(FileOrPath fileOrPath) {
        try {
            return new FileOutputStream(fileOrPath.getFile());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static FileInputStream getInputStream(FileOrPath fileOrPath) {
        try {
            return new FileInputStream(fileOrPath.getFile());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getExtension(File file) {
        String extension = "";

        int index = file.getName().lastIndexOf('.');
        if (index > 0) {
            extension = file.getName().substring(index + 1);
        }

        return extension;
    }

    public static String getCleanName(File file) {
        String extension = file.getName().replaceAll(getExtension(file), "");
        return extension.length() > 1 ? extension.substring(0, extension.length() - 1) : extension;
    }

}
