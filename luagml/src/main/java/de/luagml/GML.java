package de.luagml;

import de.enwaffel.randomutils.file.FileOrPath;
import de.enwaffel.randomutils.file.FileUtil;
import de.luagml.lib.GMLGameLib;
import de.luagml.lib.game.lib_Renderer;
import de.luagml.lib.game.lib_Window;
import org.luaj.vm2.Globals;
import org.luaj.vm2.Lua;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Prototype;
import org.luaj.vm2.compiler.LuaC;
import org.luaj.vm2.lib.jse.JsePlatform;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class GML {

    public GML() {

    }

    public void run() {
        Document doc = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING,true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(new File(G.dir + File.separator + "proj.xml"));
            doc.normalize();
        } catch (Exception e) {
            e.printStackTrace();
            Report.unexpectedError();
        }
        assert doc != null;
        Element root = doc.getElementById("project");

        G.g = JsePlatform.standardGlobals();
        loadLibraries();
        loadScripts();
    }

    public void loadLibraries() {
        G.g.load(new GMLGameLib());
        G.g.load(new lib_Window());
        G.g.load(new lib_Renderer());
    }

    public void loadScripts() {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> e.printStackTrace());
        LuaValue chunk = G.g.loadfile(G.dir + File.separator + "src" + File.separator + "test.lua");
        chunk.call();
        //Globals globals = JsePlatform.standardGlobals();
        //LuaValue chunk = globals.load("print 'hello, world'");
        //chunk.call();
    }


    public static void main(String[] args) {
        for (int i = 0;i < args.length;i++) {
            String str = args[i];
            if (str.contains("-")) {
                String argName = str.replaceAll("-", "");
                String arg = args.length > (i + 1) ? args[i + 1] : "";
                switch (argName) {
                    case "dir": {
                        G.dir = arg;
                        break;
                    }
                }
            }
        }
        if (G.dir.equals("")) Report.unexpectedError();
        new GML().run();
    }

}
