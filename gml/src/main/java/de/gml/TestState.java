package de.gml;

import de.enwaffel.randomutils.file.FileOrPath;

public class TestState extends State {

    public static Sprite sprite;

    @Override
    public void create() {
        Sound s = GML.loadSound(FileOrPath.path("test.wav"));
        Sound s1 = GML.loadSound(FileOrPath.path("test1.wav"));
        sprite = new Sprite();
        sprite.setWidth(300);
        sprite.setHeight(300);
        sprite.setX(0);
        sprite.setY(0);
        add(sprite);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

}
