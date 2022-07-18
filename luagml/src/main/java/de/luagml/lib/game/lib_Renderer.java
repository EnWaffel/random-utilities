package de.luagml.lib.game;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;

public class lib_Renderer extends TwoArgFunction {

    public lib_Renderer() {
    }

    @Override
    public LuaValue call(LuaValue modName, LuaValue env) {
        LuaValue lib = tableOf();
        lib.set("new", new ZeroArgFunction() {
            @Override
            public LuaValue call() {
                return _newRenderer();
            }
        });
        env.get("package").get("loaded").set("Renderer", lib);
        return lib;
    }

    public LuaTable _newRenderer() {
        LuaTable table = new LuaTable();
        table.set("glbegin", new OneArgFunction() {
            @Override
            public LuaValue call(LuaValue luaValue) {
                return null;
            }
        });
        table.set("vertex2", new TwoArgFunction() {
            @Override
            public LuaValue call(LuaValue luaValue, LuaValue luaValue1) {
                return null;
            }
        });
        table.set("glend", new ZeroArgFunction() {
            @Override
            public LuaValue call() {
                return null;
            }
        });
        return table;
    }

}
