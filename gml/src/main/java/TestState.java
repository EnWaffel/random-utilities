import de.enwaffel.randomutils.file.FileOrPath;
import de.gml.GML;
import de.gml.Sound;
import de.gml.Sprite;
import de.gml.State;

public class TestState extends State {

    public static Sprite sprite;

    @Override
    public void create() {
        Sound s = GML.loadSound(FileOrPath.path("test.wav"));
        Sound s1 = GML.loadSound(FileOrPath.path("test1.wav"));
        sprite = new Sprite();
        sprite.setWidth(100);
        sprite.setHeight(100);
        sprite.setX(GML.getProperties().get("windowWidth").i());
        sprite.setY(GML.getProperties().get("windowHeight").i());
        //sprite.setTexture(GML.loadImage(FileOrPath.path("img.png")));
        add(sprite);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

}
