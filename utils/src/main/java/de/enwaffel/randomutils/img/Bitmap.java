package de.enwaffel.randomutils.img;

import de.enwaffel.randomutils.file.FileOrPath;
import org.json.JSONArray;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Bitmap {

    public static int[][] generateBitmap(FileOrPath fileOrPath) throws Exception {
        BufferedImage img = ImageIO.read(fileOrPath.getFile());
        int[][] map = new int[img.getWidth() * img.getHeight() + 1][4];
        map[0] = new int[]{img.getWidth(), img.getHeight()};
        int x = 0, y = 0;
        for (int i = 1;i < img.getWidth() * img.getHeight() + 1;i++) {
            Color color = new Color(img.getRGB(x, y), true);
            map[i] = new int[]{color.getAlpha(), color.getRed(), color.getGreen(), color.getBlue()};
            x++;
            if (x >= img.getWidth()) {
                x = 0;
                y++;
            }
        }
        return map;
    }

    public static JSONArray asJson(int[][] map) {
        JSONArray arr = new JSONArray();
        for (int i = 0;i < map.length;i++) {
            int[] a = map[i];
            if (i == 0) {
                arr.put(new JSONArray().put(a[0]).put(a[1]));
            } else {
                arr.put(new JSONArray().put(a[0]).put(a[1]).put(a[2]).put(a[3]));
            }
        }
        return arr;
    }

}
