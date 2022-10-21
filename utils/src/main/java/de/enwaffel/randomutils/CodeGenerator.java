package de.enwaffel.randomutils;

import java.util.Random;

public final class CodeGenerator {

    private static final char[] letters = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final Random rng = new Random();

    public static String generate(int length, boolean useLetters) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0;i < length;i++) {
            boolean l = rng.nextBoolean();
            if (l && useLetters) {
                char c = letters[rng.nextInt(letters.length)];
                builder.append(c);
            } else {
                builder.append(rng.nextInt(9));
            }
        }
        return builder.toString();
    }

}
