package de.enwaffel.randomutils.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileOrPath {

    private static int scannedFolders;
    private static int foundFiles;

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

    public static FileOrPath search(FileOrPath folder, String name) {
        scannedFolders = 0;
        foundFiles = 0;
        return FileOrPath.path(findPath(folder.getFile(), name));
    }

    private static String findPath(File startFolder, String name) {
        List<File> files = list_all_files(startFolder);
        for (File f : files) {
            if (f.getName().equals(name)) {
                return f.getPath();
            }
        }
        return "";
    }

    private static List<File> list_all_files(File folder) {
        List<File> files = new ArrayList<>();
        File[] listedFiles = folder.listFiles();
        if (listedFiles != null) {
            scannedFolders++;
            for (File f : listedFiles) {
                if (f.isDirectory()) {
                    files.addAll(list_all_files(f));
                }
                files.add(f);
                foundFiles++;
            }
        }
        return files;
    }

    @Override
    public String toString() {
        return getPath();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileOrPath that = (FileOrPath) o;
        return path.equals(that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path);
    }

}
