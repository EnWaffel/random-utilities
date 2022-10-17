package de.gml;

import de.enwaffel.randomutils.Properties;
import de.enwaffel.randomutils.file.FileOrPath;
import de.enwaffel.randomutils.file.FileUtil;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.VarArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;
import org.luaj.vm2.lib.jse.JsePlatform;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * USE THIS CLASS ONLY FOR LUA PROJECTS!!!!!!!
 */
public class GMLMain {

    protected static Globals g = JsePlatform.standardGlobals();
    protected static Properties p;

    public static void main(String[] args) {
        if (args.length < 1) {
            ERR("GML Error: Usage: <test, build>");
            System.exit(-1);
        }

        p = new Properties();

        switch (args[0]) {
            case "test": {
                p.set("command", "test");
                loadProjectFile();
                run();
            }
            case "build": {
                p.set("command", "build");
                loadProjectFile();
                compile();
            }
            default: {
                System.out.println(args[0]);
                //ERR("GML Error: Usage: <test, build>");
                //System.exit(-1);
            }
        }
    }

    protected static void run() {
        g.set("GML", new LL.LL_GML());
    }

    protected static void compile() {

    }

    protected static void loadProjectFile() {
        try {
            String workingDirectory = Paths.get("").toAbsolutePath().toString();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING,true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(workingDirectory + "/project.xml"));
            doc.normalize();
            Element root = doc.getDocumentElement();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static LuaValue ERR(String err) {
        System.err.println(err);
        return LuaValue.NIL;
    }

    protected static boolean isLuaState(LuaTable table) {
        return !table.get("create").isnil() && !table.get("update").isnil() && !table.get("destroy").isnil();
    }

    protected static class LL {

        protected static class LL_GML extends LuaTable {

            protected LL_GML() {
                set("init", new VarArgFunction() {
                    @Override
                    public Varargs invoke(Varargs varargs) {
                        if (varargs.narg() < 4) return ERR("Not enough args!");
                        //if (isLuaState(varargs.arg(1).checktable())) return ERR("table is not a valid lua state");
                        GML.init(new LL_StateAdapter(varargs.arg(1).checktable()), varargs.arg(2).tojstring(), varargs.arg(3).toint(), varargs.arg(4).toint());
                        return LuaValue.TRUE;
                    }
                });
                set("loadSound", new OneArgFunction() {
                    @Override
                    public LuaValue call(LuaValue luaValue) {
                        return new LL_Sound(GML.loadSound(FileOrPath.path(luaValue.tojstring())));
                    }
                });

                set("exit", new OneArgFunction() {
                    @Override
                    public LuaValue call(LuaValue luaValue) {
                        System.exit(luaValue.toint());
                        return null;
                    }
                });
                set("switchState", new OneArgFunction() {
                    @Override
                    public LuaValue call(LuaValue luaValue) {
                        return null;
                    }
                });
            }

            class LL_StateAdapter extends State {

                private final LuaTable luaState;

                protected LL_StateAdapter(LuaTable luaState) {
                    this.luaState = luaState;
                    g.set("add", new OneArgFunction() {
                        @Override
                        public LuaValue call(LuaValue luaValue) {
                            return null;
                        }
                    });
                }

                @Override
                public void create() {
                    luaState.get("create").call();
                }

                @Override
                public void update(float delta) {
                    super.update(delta);
                    luaState.get("update").call(LuaValue.valueOf(delta));
                }

                @Override
                public void destroy() {
                    luaState.get("destroy").call();
                }

            }

            class LL_Sprite extends LuaTable {

                private final Sprite sprite;

                protected LL_Sprite() {
                    sprite = new Sprite();

                    set("setX", new OneArgFunction() {
                        @Override
                        public LuaValue call(LuaValue luaValue) {

                            return LuaValue.TRUE;
                        }
                    });
                }

            }

            class LL_Sound extends LuaTable {

                protected final Sound sound;

                protected LL_Sound(Sound sound) {
                    this.sound = sound;

                    set("play", new ZeroArgFunction() {
                        @Override
                        public LuaValue call() {
                            sound.play();
                            return LuaValue.TRUE;
                        }
                    });
                    set("stop", new ZeroArgFunction() {
                        @Override
                        public LuaValue call() {
                            sound.stop();
                            return LuaValue.TRUE;
                        }
                    });
                    set("pause", new ZeroArgFunction() {
                        @Override
                        public LuaValue call() {
                            sound.pause();
                            return LuaValue.TRUE;
                        }
                    });
                    set("getPosition", new ZeroArgFunction() {
                        @Override
                        public LuaValue call() {
                            return LuaValue.valueOf(sound.getPosition());
                        }
                    });
                }

            }

        }

    }

}
