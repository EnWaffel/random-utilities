package de.enwaffel.randomutils.file;

import java.io.File;

public class FileOrPath {

    private String path;
    private File file;

    private FileOrPath(String path) {
        this.path = path;
    }

    private FileOrPath(File file) {
        this.file = file;
    }

    public String getPath() {
        return path != null ? path : file.getPath();
    }

    public File getFile() {
        return file != null ? file : new File(path);
    }

    public static FileOrPath file(File file) {
        return new FileOrPath(file);
    }

    public static FileOrPath path(String path) {
        return new FileOrPath(path);
    }

}
