package de.enwaffel.randomutils.encode;

public class EncodeUtil {

    private static final String defaultSeparator = "-";
    private static final EncoderMap defaultMap = new EncoderMap();

    public String to(String str) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        for (char c : str.toCharArray()) {
            result.append(defaultSeparator);
            result.append(defaultMap.getReplacementCharacter(c));
            result.append(defaultSeparator);
            i++;
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
        int i = 0;
        for (char c : str.toCharArray()) {
            result.append(separator);
            result.append(defaultMap.getReplacementCharacter(c));
            result.append(separator);
            i++;
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

    public String to(String str, String separator, EncoderMap map) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        for (char c : str.toCharArray()) {
            result.append(separator);
            result.append(map.getReplacementCharacter(c));
            result.append(separator);
            i++;
        }
        return result.toString();
    }

    public String from(String str, String separator, EncoderMap map) {
        StringBuilder result = new StringBuilder();
        String[] decode = str.split(separator);
        for (String str1 : decode) {
            Character c = map.getOriginalCharacter(str1);
            if (c != null)
                result.append(c);
        }
        return result.toString();
    }

    public String to(String str, EncoderMap map) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        for (char c : str.toCharArray()) {
            result.append(defaultSeparator);
            result.append(map.getReplacementCharacter(c));
            result.append(defaultMap);
            i++;
        }
        return result.toString();
    }

    public String from(String str, EncoderMap map) {
        StringBuilder result = new StringBuilder();
        String[] decode = str.split(defaultSeparator);
        for (String str1 : decode) {
            Character c = map.getOriginalCharacter(str1);
            if (c != null)
                result.append(c);
        }
        return result.toString();
    }

}
