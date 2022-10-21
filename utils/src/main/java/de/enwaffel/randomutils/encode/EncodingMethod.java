package de.enwaffel.randomutils.encode;

public interface EncodingMethod {
    byte[] encode(String str);
    String decode(byte[] bytes);
    String getId();
}
