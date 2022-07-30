package de.enwaffel.randomutils.io;

import de.enwaffel.randomutils.file.FileOrPath;
import de.enwaffel.randomutils.file.FileUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpFile {

    private URL url;

    public HttpFile(URL url) {
        if (url == null) throw new IllegalArgumentException("url cannot be null!");
        this.url = url;
    }

    public HttpFile(String url) {
        if (url == null) throw new IllegalArgumentException("url cannot be null!");
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public ByteBuffer downloadContent() {
        try {
            ByteBuffer buffer = new ByteBuffer();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream in = conn.getInputStream();

            int readByte;
            while ((readByte = in.read()) >= 0) {
                buffer.add(readByte);
            }
            in.close();
            conn.disconnect();

            return buffer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public FileOrPath downloadContentToFile(FileOrPath fileOrPath) {
        FileUtil.writeFile(downloadContent(), fileOrPath);
        return fileOrPath;
    }

}
