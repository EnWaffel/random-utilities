package de.enwaffel.randomutils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONUtil {

    public static <T> List<T> asList(JSONArray array) {
        List<T> list = new ArrayList<>();
        for (Object o : array) {
            list.add((T) o);
        }
        return list;
    }

    public static boolean isValidObject(String str) {
        try { new JSONObject(str); return true; } catch (Exception ignored) { return false; }
    }

    public static boolean isValidArray(String str) {
        try { new JSONArray(str); return true; } catch (Exception ignored) { return false; }
    }

}
