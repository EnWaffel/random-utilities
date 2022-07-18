package de.enwaffel.lib.java_natives;

import de.enwaffel.api.APIImpl;
import de.enwaffel.api.NativeAPI;
import de.enwaffel.randomutils.file.FileOrPath;
import de.enwaffel.randomutils.file.FileUtil;
import de.enwaffel.util.Validate;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;

import java.io.IOException;
import java.io.OutputStream;

public class main extends NativeAPI {

    public main(APIImpl api) {
        super(api);
        api.g().set("stream", new stream());
    }

    class stream extends LuaTable {

        public stream() {
            set("output", new stream_output());
        }

        class stream_output extends LuaTable {

            public stream_output() {
                set("fromFile", new OneArgFunction() {
                    @Override
                    public LuaValue call(LuaValue luaValue) {
                        Validate.str(luaValue);
                        return new stream_output_interface(FileUtil.getOutputStream(FileOrPath.path(luaValue.tojstring())));
                    }
                });
            }

        }

    }

    class stream_output_interface extends LuaTable {

        public stream_output_interface(OutputStream stream) {
            set("write", new OneArgFunction() {
                @Override
                public LuaValue call(LuaValue luaValue) {
                    Validate.i(luaValue);
                    try {
                        stream.write(Integer.parseInt(luaValue.tojstring()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return LuaValue.valueOf(true);
                }
            });
            set("flush", new ZeroArgFunction() {
                @Override
                public LuaValue call() {
                    try {
                        stream.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            });
            set("close", new ZeroArgFunction() {
                @Override
                public LuaValue call() {
                    try {
                        stream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            });
        }

    }

}
