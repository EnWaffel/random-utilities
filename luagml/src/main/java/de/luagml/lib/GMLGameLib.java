package de.luagml.lib;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;

public class GMLGameLib extends TwoArgFunction {

    public GMLGameLib() {
    }

    @Override
    public LuaValue call(LuaValue modName, LuaValue env) {
        LuaValue lib = tableOf();
        lib.set("test", new ZeroArgFunction() {
            @Override
            public LuaValue call() {
                return null;
            }
        });
        env.get("package").get("loaded").set("GML_GAME", lib);
        return lib;
    }

}
