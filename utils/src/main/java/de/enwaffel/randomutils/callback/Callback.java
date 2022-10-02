package de.enwaffel.randomutils.callback;

import de.enwaffel.randomutils.Properties;

/**
 * A callback interface with can be called asynchronously
 */
public interface Callback {
    /**
     * Called when something happens.
     * @param key The key of the action.
     * @param properties Additional data.
     */
    void call(String key, Properties properties);
}
