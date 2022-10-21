package de.enwaffel.randomutils.encode;

import de.enwaffel.randomutils.encode.exception.EncodingException;

import java.util.HashMap;

public class EncodeUtil {

    private static final HashMap<Class<? extends EncodingMethod>, EncodingMethod> methods = new HashMap<>();

    static {
        try {
            registerEncodingMethod(TTTEncodingMethod.class);
        } catch (EncodingException e) {
            throw new RuntimeException(e);
        }
    }


    public static void registerEncodingMethod(Class<? extends EncodingMethod> clazz) throws EncodingException {
        try {
            if (clazz.getDeclaredConstructors().length < 1) throw new EncodingException("EncodingMethod class has no constructors");
            if (clazz.getDeclaredConstructors()[0].getParameters().length > 0) throw new EncodingException("EncodingMethod class's constructor has too many arguments");
            EncodingMethod method = clazz.getDeclaredConstructor().newInstance();
            methods.put(clazz, method);
        } catch (Exception e) {
            throw new EncodingException(e);
        }
    }

    public static EncodingMethod getEncodingMethod(Class<? extends EncodingMethod> clazz) {
        try {
            if (methods.containsKey(clazz)) return methods.get(clazz);
        } catch (Exception ignored) {}
        return null;
    }

}
