package de.luagml.lib.game;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;

public class lib_Window extends TwoArgFunction {

    public lib_Window() {
    }

    @Override
    public LuaValue call(LuaValue modName, LuaValue env) {
        LuaValue lib = tableOf();
        lib.set("new", new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue luaValue) {
                return _newWindow((LuaTable) luaValue);
            }
        });
        env.get("package").get("loaded").set("Window", lib);
        return lib;
    }

    public LuaTable _newWindow(LuaTable renderer) {
        LuaTable table = new LuaTable();
        return table;
    }

}
