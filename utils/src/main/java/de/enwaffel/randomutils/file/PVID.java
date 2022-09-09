package de.enwaffel.randomutils.file;

import de.enwaffel.randomutils.Bitmap;
import de.enwaffel.randomutils.io.ByteBuffer;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PVID {

    private static final int v = 1;

    public static void savePVID(FileOrPath images, int fps, FileOrPath endFile) throws Exception {
        FileOutputStream fos = new FileOutputStream(endFile.getFile());
        fos.write(0x01);
        fos.write(fps);
        fos.write(0x01);
        File[] files = images.getFile().listFiles();
        assert files != null;
        List<int[][]> a = new ArrayList<>();
        for (File f : files) {
            a.add(Bitmap.generateBitmap(FileOrPath.file(f)));
            System.out.println("BITMAP GENERATED FOR : " + f.getName());
        }
        for (int i = 0;i < a.size();i++) {
            int[][] _a = a.get(i);
            for (int _i = 0;_i < _a.length;_i++) {
                int[] __a = _a[_i];
                fos.write(__a.length > 0 ? __a[0] : 0);
                fos.write(__a.length > 1 ? __a[1] : 0);
                fos.write(__a.length > 2 ? __a[2] : 0);
                fos.write(__a.length > 3 ? __a[3] : 0);
                if (_a[i] != __a) fos.write(0x01F);
                System.out.println(_i + " / " + _a.length);
            }
            fos.write(0x02);
            fos.write(0x03);
            System.out.println("BITMAP SAVED FOR : " + i);
        }
        fos.flush();
        fos.close();
    }

}
