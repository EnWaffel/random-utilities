package de.enwaffel.randomutils.file;

import java.io.File;

public class FileOrPath {

    private String path;
    private File file;

    public FileOrPath(String path) {
        this.path = path;
    }

    public FileOrPath(File file) {
        this.file = file;
    }

    public String getPath() {
        return path;
    }

    public File getFile() {
        if (path == null) {
            return file;
        } else {
            return new File(path);
        }
    }

}
