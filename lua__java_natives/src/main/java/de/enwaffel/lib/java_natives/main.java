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

import java.io.OutputStream;
import java.util.Arrays;

public class main extends NativeAPI {

    public main(APIImpl api) {
        super(api);
        api.g().set("stream", new stream());
        api.g().set("bytes", new bytes());
    }

    static class stream extends LuaTable {

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

    static class stream_output_interface extends LuaTable {

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
            set("writeBytes", new OneArgFunction() {
                @Override
                public LuaValue call(LuaValue luaValue) {
                    byte[] bytes = new byte[luaValue.length()];
                    for (int i = 0;i < luaValue.length();i++) {
                        bytes[i] = (byte) Integer.parseInt(luaValue.get(i).tojstring());
                    }
                    try {
                        stream.write(bytes);
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

    static class bytes extends LuaTable {

        public bytes() {
            set("getBytes", new OneArgFunction() {
                @Override
                public LuaValue call(LuaValue luaValue) {
                    LuaTable bytesTable = new LuaTable();
                    byte[] bytes = luaValue.tojstring().getBytes();
                    for (int i = 0;i < bytes.length;i++) {
                        int b = bytes[i];
                        System.out.println(b);
                        System.out.println(i + 1);
                        System.out.println(LuaValue.valueOf(b));
                        bytesTable.set(i + 1, LuaValue.valueOf(b));
                    }
                    System.out.println(Arrays.toString(bytes));
                    for (int i = 0;i < bytesTable.length();i++) {
                        System.out.println(bytesTable.get(i));
                    }
                    return bytesTable;
                }
            });
        }

    }

}
