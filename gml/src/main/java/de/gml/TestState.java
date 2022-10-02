package de.gml;

import de.enwaffel.randomutils.file.FileOrPath;

public class TestState extends State {

    public static Sprite sprite;

    @Override
    public void create() {
        Sound s = GML.loadSound(FileOrPath.path("test.wav"));
        Sound s1 = GML.loadSound(FileOrPath.path("test1.wav"));
        sprite = new Sprite();
        sprite.setWidth(500);
        sprite.setHeight(500);
        sprite.setX(10);
        sprite.setY(10);
        LoadedAnimation anim = SparrowAtlas.load(FileOrPath.path("a.xml"), FileOrPath.path("a.png"));
        sprite.getAnimationController().load(anim);
        sprite.getAnimationController().play("BF idle dance");
        add(sprite);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

}
