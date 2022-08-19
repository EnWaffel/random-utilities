package de.enwaffel.randomutils;

import org.json.JSONArray;
import org.json.JSONObject;

public class LuaUtil {

    public static String jsonToTable(JSONObject json) {
        StringBuilder result = new StringBuilder(jsonToTable_obj("", json, false, true));
        if (result.charAt(result.length() - 2) == ',') result.deleteCharAt(result.length() - 2);
        if (result.charAt(result.length() - 1) == ' ') result.deleteCharAt(result.length() - 1);
        if (result.charAt(1) == ',') result.deleteCharAt(1);
        return result.toString();
    }

    private static String jsonToTable_obj(String key, JSONObject object, boolean __, boolean ___) {
        StringBuilder result = new StringBuilder();
        if (___) {
            if (__) {
                result.append("[\"").append(key).append("\"]").append(" = ").append("{");
            }
            else {
                result.append("{");
            }
        } else {
            if (__) {
                result.append(",[\"").append(key).append("\"]").append(" = ").append("{");
            } else {
                result.append(",{");
            }
        }
        for (String k : object.keySet()) {
            if (object.get(k) instanceof JSONObject) {
                result.append(jsonToTable_obj(k, object.getJSONObject(k), true, false));
            } else if (object.get(k) instanceof JSONArray) {
                result.append(jsonToTable_arr(k, object.getJSONArray(k), true, false));
            } else {
                result.append(jsonToTable_value(k, object.get(k)));
            }
        }
        result.append("},");
        return result.toString();
    }

    private static String jsonToTable_arr(String key, JSONArray array, boolean __, boolean ___) {
        StringBuilder result = new StringBuilder();
        if (___) {
            if (__) {
                result.append("[\"").append(key).append("\"]").append(" = ").append("{");
            }
            else {
                result.append("{");
            }
        } else {
            if (__) {
                result.append(",[\"").append(key).append("\"]").append(" = ").append("{");
            } else {
                result.append(",{");
            }
        }
        for (Object o : array.toList()) {
            if (o instanceof JSONObject) {
                result.append(jsonToTable_obj("", (JSONObject) o, true, false));
            } else if (o instanceof JSONArray) {
                result.append(jsonToTable_arr("", (JSONArray) o, false, false));
            } else {
                result.append((o.getClass().equals(String.class) ? "\"" + o + "\"" : o));
            }
        }
        result.append("}, ");
        return result.toString();
    }

    private static String jsonToTable_value(String key, Object o) {
        return key.contains(" ") ? "[\"" + key + "\"]" + " = " + o : key + " = " + (o.getClass().equals(String.class) ? "\"" + o + "\"" : o);
    }

}
