package de.enwaffel;

import de.enwaffel.api.APIImpl;
import de.enwaffel.randomutils.file.FileOrPath;
import de.enwaffel.randomutils.file.FileUtil;
import org.json.JSONObject;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static Globals globals;

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        globals = JsePlatform.standardGlobals();

        File apisFolder = new File(args[0] + File.separator + "api");
        if (apisFolder.exists()) {
            for (File f : apisFolder.listFiles()) {
                if (f.isDirectory()) {
                    File apiFile = new File(f.getPath() + File.separator + ".api");
                    if (apiFile.exists()) {
                        JSONObject json = FileUtil.readJSON(FileOrPath.file(apiFile));
                        List<Object> excluded = new ArrayList<>(json.getJSONArray("exclude").toList());
                        String classPath = json.getString("class-path");
                        Class<?> c = ClassLoader.getSystemClassLoader().loadClass(classPath);
                        c.getDeclaredConstructor(APIImpl.class).newInstance((APIImpl) () -> globals);
                        for (File _f : f.listFiles()) {
                            if (_f.isFile() && _f.getName().contains(".lua") && !excluded.contains(_f.getName())) {
                                LuaValue chunk = globals.loadfile(_f.getAbsolutePath());
                                chunk.call();
                            }
                        }
                    }
                }
            }
        }
        for (File f : new File(args[0]).listFiles()) {
            if (f.getName().contains(".lua")) {
                LuaValue chunk = globals.loadfile(f.getAbsolutePath());
                chunk.call();
            }
        }
    }

}
