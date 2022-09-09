package de.enwaffel.randomutils;

import java.util.Random;

public class MathUtil {

    public static long randomSeed() {
        Random random = new Random();
        return random.nextLong();
    }

    public static int remapToRange(int value, int start1, int stop1, int start2, int stop2) {
        return start2 + (value - start1) * ((stop2 - start2) / (stop1 - start1));
    }

    public static double remapToRange(double value, double start1, double stop1, double start2, double stop2) {
        return start2 + (value - start1) * ((stop2 - start2) / (stop1 - start1));
    }

    public static int trim(int value, int maxDigits) {
        int[] digits = getDigits(value);
        StringBuilder result = new StringBuilder();

        for (int i = 0;i < maxDigits;i++) {
            if (i < digits.length) {
                result.append(digits[i]);
            }
        }
        return Integer.parseInt(result.toString());
    }

    public static int[] getDigits(int value) {
        String v = String.valueOf(value);
        int[] result = new int[v.length()];
        char[] chars = v.toCharArray();

        for (int i = 0;i < chars.length;i++) {
            result[i] = Integer.parseInt(String.valueOf(chars[i]));
        }
        return result;
    }

    public static double percentOf(double v, double of, double p) {
        return v * (p / of);
    }

}
