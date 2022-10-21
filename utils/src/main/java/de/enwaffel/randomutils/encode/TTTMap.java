package de.enwaffel.randomutils.encode;

import de.enwaffel.randomutils.MathUtil;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TTTMap {

    protected static final long defaultSeed = Long.parseLong(new String(Base64.getDecoder().decode(new byte[]{76, 84, 99, 119, 78, 84, 103, 120, 79, 84, 89, 49, 77, 68, 81, 50, 78, 68, 81, 121, 78, 106, 81, 51, 78, 68, 82})));
    protected static final char[] defaultChars = new char[]{' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '}', '[', ']', '\"', '+', '-', '*', '/', '\\', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', ',', '.', '_', '!', '?', '=', '(', ')', '\n'};

    public final HashMap<Character, String> chars;

    private TTTMap(HashMap<Character, String> chars) {
        this.chars = chars;
    }

    public String getReplacementValue(char c) {
        return chars.getOrDefault(c, "�");
    }

    public Character getOriginalCharacter(String c) {
        for (Map.Entry<Character,String> set : chars.entrySet()) {
            if (set.getValue().equals(c)) {
                return set.getKey();
            }
        }
        return null;
    }

    public static TTTMap generateMap() {
        return generateMap(MathUtil.randomSeed(), defaultChars);
    }

    public static TTTMap generateMap(long seed) {
        return generateMap(seed, defaultChars);
    }

    public static TTTMap generateMap(long seed, char... chars) {
        Random random = new Random(seed);
        HashMap<Character, String> map = new HashMap<>();
        for (char c : chars) {
            String num = String.valueOf(random.nextInt(chars.length));
            while (map.containsValue(num)) {
                num = String.valueOf(random.nextInt(chars.length));
            }
            map.put(c, num);
        }
        return new TTTMap(map);
    }

    public static char[] defaultChars() {
        return defaultChars;
    }

}
