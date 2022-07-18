package de.enwaffel.randomutils.encode;

import de.enwaffel.randomutils.MathUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EncoderMap {

    protected static final long defaultSeed = -7058196504644264744L;
    protected static final char[] defaultChars = new char[]{' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '}', '[', ']', '\"', '+', '-', '*', '/', '\\', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', ',', '.', '_', '!', '?', '=', '(', ')', '\n'};

    private final HashMap<Character,String> chars;

    private EncoderMap(HashMap<Character, String> chars) {
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

    public static EncoderMap generateMap() {
        return generateMap(MathUtil.randomSeed());
    }

    public static EncoderMap generateMap(long seed, char... chars) {
        Random random = new Random(seed);
        HashMap<Character, String> map = new HashMap<>();
        for (char c : chars) {
            String num = String.valueOf(random.nextInt(chars.length));
            while (map.containsValue(num)) {
                num = String.valueOf(random.nextInt(chars.length));
            }
            map.put(c, num);
        }
        return new EncoderMap(map);
    }

}
