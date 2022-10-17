package de.enwaffel.mc;

import de.enwaffel.randomutils.ReflectUtil;

public class ServerUtils {

    public static <T extends MCServerUtils> T getServerUtils(T type, String api) {
        try {
            return (T) ReflectUtil.getClass(type.getClass().getSimpleName() + "Impl", "de.enwaffel.mc.bukkit").getDeclaredConstructor(String.class).newInstance(api);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
