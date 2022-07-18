package de.enwaffel.randomutils.callback;

import de.enwaffel.randomutils.Properties;

import java.util.HashMap;
import java.util.Map;

public class CallbackHandler {

    private final HashMap<String, Callback> callbacks = new HashMap<>();

    public void attach(String key, Callback callback) {
        callbacks.put(key, callback);
    }

    public void detach(String key) {
        callbacks.remove(key);
    }

    public void call(String key, Properties properties) {
        for (Map.Entry<String, Callback> set : callbacks.entrySet()) {
            if (set.getKey().equals(key)) set.getValue().call(key, properties);
        }
    }

}
