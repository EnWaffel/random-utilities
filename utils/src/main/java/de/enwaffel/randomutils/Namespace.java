package de.enwaffel.randomutils;

public class Namespace {

    private final String key;
    private final String value;

    private Namespace(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return key + ":" + value;
    }

    public static Namespace get(String namespace) {
        String[] split = namespace.split(":");
        return new Namespace(split[0], split[1]);
    }

}
