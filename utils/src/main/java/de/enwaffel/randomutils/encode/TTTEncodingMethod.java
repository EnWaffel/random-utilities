package de.enwaffel.randomutils.encode;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Base64;

public class TTTEncodingMethod implements EncodingMethod {

    private final String defaultSeparator = "-";
    private final TTTMap defaultMap = TTTMap.generateMap(TTTMap.defaultSeed, TTTMap.defaultChars);

    protected TTTEncodingMethod() {
    }

    public String to(String str) {
        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            result.append(defaultSeparator);
            result.append(defaultMap.getReplacementValue(c));
            result.append(defaultSeparator);
        }
        return result.toString();
    }

    public String from(String str) {
        StringBuilder result = new StringBuilder();
        String[] decode = str.split(defaultSeparator);
        for (String str1 : decode) {
            Character c = defaultMap.getOriginalCharacter(str1);
            if (c != null)
                result.append(c);
        }
        return result.toString();
    }

    public String to(String str, String separator) {
        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            result.append(separator);
            result.append(defaultMap.getReplacementValue(c));
            result.append(separator);
        }
        return result.toString();
    }

    public String from(String str, String separator) {
        StringBuilder result = new StringBuilder();
        String[] decode = str.split(separator);
        for (String str1 : decode) {
            Character c = defaultMap.getOriginalCharacter(str1);
            if (c != null)
                result.append(c);
        }
        return result.toString();
    }

    public String to(String str, String separator, TTTMap map) {
        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            result.append(separator);
            result.append(map.getReplacementValue(c));
            result.append(separator);
        }
        return result.toString();
    }

    public String from(String str, String separator, TTTMap map) {
        StringBuilder result = new StringBuilder();
        String[] decode = str.split(separator);
        for (String str1 : decode) {
            Character c = map.getOriginalCharacter(str1);
            if (c != null)
                result.append(c);
        }
        return result.toString();
    }

    public String to(String str, TTTMap map) {
        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            result.append(defaultSeparator);
            result.append(map.getReplacementValue(c));
            result.append(defaultSeparator);
        }
        return result.toString();
    }

    public String from(String str, TTTMap map) {
        StringBuilder result = new StringBuilder();
        String[] decode = str.split(defaultSeparator);
        for (String str1 : decode) {
            Character c = map.getOriginalCharacter(str1);
            if (c != null)
                result.append(c);
        }
        return result.toString();
    }

    public String to(String str, int times) {
        String result = str;
        for (int i = 0;i < times;i++) {
            result = to(result);
        }
        return result;
    }

    public String to(String str, String separator, TTTMap map, int times) {
        String result = str;
        for (int i = 0;i < times;i++) {
            result = to(result, separator, map);
        }
        return result;
    }

    public String to(String str, String separator, int times) {
        String result = str;
        for (int i = 0;i < times;i++) {
            result = to(result, separator);
        }
        return result;
    }

    public String from(String str, String separator, TTTMap map, int times) {
        String result = str;
        for (int i = 0;i < times;i++) {
            result = from(result, separator, map);
        }
        return result;
    }

    public String from(String str, String separator, int times) {
        String result = str;
        for (int i = 0;i < times;i++) {
            result = from(result, separator);
        }
        return result;
    }

    public String from(String str, TTTMap map, int times) {
        String result = str;
        for (int i = 0;i < times;i++) {
            result = from(result, map);
        }
        return result;
    }

    public String from(String str, int times) {
        String result = str;
        for (int i = 0;i < times;i++) {
            result = from(result);
        }
        return result;
    }


    @Override
    public byte[] encode(String str) {
        byte[] result = new byte[0];
        for (char c : str.toCharArray()) {
            byte b = (byte) c;
            result = ArrayUtils.add(result, (byte) (b << 10));
        }
        return result;
    }

    @Override
    public String decode(byte[] bytes) {
        return null;
    }

    @Override
    public String getId() {
        return "TTT";
    }

}
