package de.enwaffel.randomutils;

public class ArrayUtil {

    public static boolean contains(Object[] array, Object object) {
        for (Object o : array) {
            if (o.equals(object)) {
                return true;
            }
        }
        return false;
    }

}
