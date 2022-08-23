package de.enwaffel.randomutils;

import de.enwaffel.randomutils.file.FileOrPath;
import org.json.JSONArray;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This is literally useless
 * This is literally useless
 * This is literally useless
 * This is literally useless
 * This is literally useless
 * This is literally useless
 * This is literally useless
 * This is literally useless
 * This is literally useless
 * This is literally useless
 * This is literally useless
 * This is literally useless
 * This is literally useless
 * This is literally useless
 * This is literally useless
 */
public class Bitmap {

    /**
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     */
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

    /**
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     */
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

    /**
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     * This is literally useless
     */
    public static String asCID(int[][] map) {
        StringBuilder b = new StringBuilder();
        b.append(map[0][0]).append(" ").append(map[0][1]).append("-");
        for (int i = 1;i < map.length;i++) {
            int[] a = map[i];
            b.append(a[0]).append(" ").append(a[1]).append(" ").append(a[2]).append(" ").append(a[3]).append("-");
        }

        return b.toString();
    }

}
