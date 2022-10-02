package de.gml;

import de.enwaffel.randomutils.file.FileOrPath;
import org.w3c.dom.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class SparrowAtlas {

    public static LoadedAnimation load(FileOrPath xml, FileOrPath image, int... fps) {
        Document doc = GML.loadXML(xml);
        TextureImage img = GML.loadImage(image);
        assert doc != null;
        HashMap<String, AnimationData> map = new HashMap<>();
        HashMap<String, List<Node>> temp = new HashMap<>();
        Element atlas = doc.getDocumentElement(); if (atlas == null) GML.ERR("No XML-Element: \"TextureAtlas\"");
        NodeList frameElements = atlas.getElementsByTagName("SubTexture");
        for (int i = 0;i < frameElements.getLength();i++) {
            Node node = frameElements.item(i);
            NamedNodeMap attrs = node.getAttributes();
            String name = formatName(attrs.getNamedItem("name").getNodeValue());
            if (!temp.containsKey(name)) temp.put(name, new ArrayList<>());
            temp.get(name).add(node);
        }

        int _i = 0;
        for (Map.Entry<String, List<Node>> set : temp.entrySet()) {
            List<TextureImage> list = new ArrayList<>();
            for (Node node : set.getValue()) {
                NamedNodeMap attrs = node.getAttributes();
                int x = Integer.parseInt(attrs.getNamedItem("x").getNodeValue()), y = Integer.parseInt(attrs.getNamedItem("y").getNodeValue()),
                width = Integer.parseInt(attrs.getNamedItem("width").getNodeValue()), height = Integer.parseInt(attrs.getNamedItem("height").getNodeValue());
                AtomicBoolean result = new AtomicBoolean(false);
                AtomicReference<TextureImage> i = new AtomicReference<>();
                GML.glManager.requestTextureB(img.b.getSubimage(x, y, width, height), (var0, var1) -> {
                    TextureImage _img = new TextureImage(var0, var1);
                    String _x = attrs.getNamedItem("frameX") != null ? attrs.getNamedItem("frameX").getNodeValue() : "0";
                    String _y = attrs.getNamedItem("frameY") != null ? attrs.getNamedItem("frameY").getNodeValue() : "0";
                    String _w = attrs.getNamedItem("frameWidth") != null ? attrs.getNamedItem("frameWidth").getNodeValue() : "0";
                    String _h = attrs.getNamedItem("frameHeight") != null ? attrs.getNamedItem("frameHeight").getNodeValue() : "0";
                    _img._offsetX = Float.parseFloat(_x);
                    _img._offsetY = Float.parseFloat(_y);
                    i.set(_img);
                    result.set(true);
                });
                while (!result.get()) Thread.onSpinWait();
                list.add(i.get());
            }

            map.put(set.getKey(), new AnimationData(list, fps.length > _i ? fps[0] : 24));
            _i++;
        }

        return () -> map;
    }

    private static String formatName(String n) {
        return n.substring(0, n.length() - 4);
    }

}
