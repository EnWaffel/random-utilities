package de.enwaffel.projectp;

import de.enwaffel.projectp.api.service.AssetProvider;
import de.enwaffel.projectp.api.service.ServiceAPI;
import de.enwaffel.projectp.internal.api.impl.AssetProviderImpl;
import de.enwaffel.randomutils.ArrayUtil;
import de.enwaffel.randomutils.ReflectUtil;
import de.enwaffel.randomutils.io.ByteBuffer;
import org.apache.commons.lang3.Validate;

import java.util.HashMap;

public final class API {

    private static boolean initialized;
    private static final HashMap<String, IAPI> apis = new HashMap<>();

    public static void init() {
        Validate.isTrue(!initialized, "already initialized!");

        for (String str : G.apis) {
            String[] splitStr = str.split("::");
            String apiName = splitStr[0];
            String apiClassName = splitStr[1];
            Class<?> clazz = null;
            try { clazz = Class.forName(apiClassName);} catch (Exception e) { e.printStackTrace(); }
            if (clazz == null) {
                System.err.println("No api class for: " + apiName);
                continue;
            }
            if (!ArrayUtil.contains(clazz.getInterfaces(), IAPI.class)) {
                System.err.println("API class: " + apiClassName + " doesn't extend IAPI class");
                continue;
            }
            IAPI apiClass = null;
            try { apiClass = getAPI((Class<? extends IAPI>) clazz); } catch (Exception e) { e.printStackTrace(); }
            if  (apiClass == null) {
                System.err.println("No API implementation for: " + apiClassName);
                continue;
            }
            apis.put(apiName, apiClass);
        }

        initialized = true;
    }

    private static IAPI getAPI(Class<? extends IAPI> apiClass) {
        try {
            Class<?> clazz = ReflectUtil.findClass("de.enwaffel.projectp.internal.api.impl." + apiClass.getSimpleName() + "Impl");
            if (ArrayUtil.contains(clazz.getInterfaces(), apiClass)) {
                return (IAPI) clazz.getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T extends IAPI> T getAPI(String name) {
        if (!initialized) init();
        return (T) apis.get(name);
    }

}
