package de.enwaffel.randomutils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringUtil {

    private static final HashMap<String, Object> replaceables = new HashMap<>();

    public static void addReplaceable(String replaceable, Object value) {
        replaceables.put(replaceable, value);
    }

    public static String replace(String str) {
        String result = str;
        for (Map.Entry<String, Object> set : replaceables.entrySet()) {
            result = result.replaceAll("&" + set.getKey(), set.getValue().toString());
        }
        return result;
    }

    public static <T> boolean containsAny(String str, T[] any) {
        for (T _any : any) {
            if (str.contains(_any.toString())) return true;
        }
        return false;
    }

    public static <T> boolean containsAny(String str, List<T> any) {
        for (T _any : any) {
            if (str.contains(_any.toString())) return true;
        }
        return false;
    }

}
