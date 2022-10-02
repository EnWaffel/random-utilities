package de.gml;

import com.jogamp.opengl.util.texture.Texture;
import de.enwaffel.randomutils.Properties;
import de.enwaffel.randomutils.file.FileOrPath;
import org.w3c.dom.Document;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class GML {

    // constants
    protected static int textureIdEnumerator = 0;
    protected static int soundIdEnumerator = 0;

    private static Properties properties;
    private static boolean initialized;
    private static Thread updateThread;
    private static Thread renderThread;
    private static int ups;
    private static int fps;
    public static int maxFPS = 60;
    public static boolean useCache = true;
    public static boolean pauseOnLostFocus = true;
    protected static boolean running = true;
    protected static State currentState;

    protected static SoundSystem soundSystem;
    protected static GLManager glManager;

    // cache

    protected static final List<Sound> cache_sound = new ArrayList<>();
    protected static final List<TextureImage> cache_image = new ArrayList<>();
    protected static final List<Camera> cameras = new ArrayList<>();

    public static void init(State initialState, String windowTitle, int windowWidth, int windowHeight) {
        properties = new Properties()
                .set("windowTitle", windowTitle)
                .set("windowWidth", windowWidth)
                .set("windowHeight", windowHeight);


        // services
        soundSystem = new SoundSystem();
        soundSystem.init(new Properties());
        glManager = new GLManager();
        glManager.init(properties);
        cameras.add(createCamera());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            soundSystem.disable();
            glManager.disable();
        }));

        run();
        initialized = true;
        switchState(initialState);
    }

    // sound

    public static Sound loadSound(FileOrPath fileOrPath) {
        check();
        Sound obj = useCache ? checkCache(fileOrPath, cache_sound) : null;
        if (obj == null) {
            obj = soundSystem.load(fileOrPath);
            if (useCache) cache_sound.add(obj);
        }
        return obj;
    }

    // image
    public static TextureImage loadImage(FileOrPath fileOrPath) {
        check();
        TextureImage obj = useCache ? checkCache(fileOrPath, cache_image) : null;
        if (obj == null) {
            obj = load_image(fileOrPath);
            if (useCache) cache_image.add(obj);
        }
        return obj;
    }

    private static TextureImage load_image(FileOrPath fileOrPath) {
        textureIdEnumerator++;
        AtomicReference<Texture> glTexture = new AtomicReference<>();
        AtomicBoolean returned = new AtomicBoolean();
        AtomicReference<BufferedImage> b = new AtomicReference<>();
        glManager.requestTexture(fileOrPath, (texture, buff) -> {
            glTexture.set(texture);
            returned.set(true);
            b.set(buff);
        });
        while (!returned.get()) Thread.onSpinWait();
        TextureImage textureImage = new TextureImage(glTexture.get(), b.get());
        textureImage.id = textureIdEnumerator;
        return textureImage;
    }

    // data file

    public static Document loadXML(FileOrPath fileOrPath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING,true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fileOrPath.getFile());
            doc.normalize();
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // camera

        public static Camera createCamera() {
        Camera camera = new Camera();
        cameras.add(camera);
        return camera;
    }

    // state

    public static void switchState(State state) {
        if (state == null) throw new IllegalArgumentException("GML Error: state cannot be null");
        if (currentState != null) currentState.destroy();
        for (Sound sound : SoundSystem.sounds) { SoundSystem.deleteSound(sound); }
        state.create();
        currentState = state;
    }

    // util

    public static Properties getProperties() {
        return properties;
    }

    public static void print(Object... o) {
        StringBuilder b = new StringBuilder();
        for (Object _o : o) { b.append(_o); if (_o != o[o.length - 1]) b.append(", "); }
        System.out.println(b);
    }

    private static void check() {
        if (!initialized) throw new RuntimeException("GML Error: not initialized");
    }

    private static <T extends Resource> T checkCache(Object obj, List<T> cache) {
        for (Resource r : cache) {
            if (obj.getClass().equals(FileOrPath.class) && r.p != null) {
                if (r.p.equals(((FileOrPath) obj).getPath())) {
                    return (T) r;
                }
            }
            if (obj.getClass().equals(BufferedImage.class) && r.p != null) {
                if (((TextureImage) r).b.equals(obj)) {
                    return (T) r;
                }
            }
        }
        return null;
    }

    protected static void ERR(String err) {
        throw new RuntimeException("GML Error: " + err);
    }

    protected static void ERR(Throwable throwable) {
        System.err.print("GML Error: ");
        throw new RuntimeException(throwable);
    }

    // game loops

    private static void run() {
        if (initialized) throw new RuntimeException("GML Error: cannot run twice");
        updateThread = new Thread(GML::run__update);
        renderThread = new Thread(GML::run__render);

        updateThread.start();
        renderThread.start();
    }

    private static void run__update() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 1000;
        double delta = 0;
        int frames = 0;
        float deltaTime = 0;

        while (!updateThread.isInterrupted()) {
            while (!running) Thread.onSpinWait();

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                delta--;
                // update
                long last_time = System.nanoTime();
                soundSystem.update(deltaTime);
                if (currentState != null) currentState.update(deltaTime);
                deltaTime = System.nanoTime() - last_time / 1000000000F;
                frames++;
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                ups = frames;
                frames = 0;
            }
        }
    }

    private static void run__render() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / maxFPS;
        double delta = 0;
        int frames = 0;

        while (!renderThread.isInterrupted()) {
            while (!running) Thread.onSpinWait();

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                delta--;
                glManager.draw();
                frames++;
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                ups = frames;
                frames = 0;
            }
        }
    }

}
