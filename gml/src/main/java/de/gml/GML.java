package de.gml;

import com.jogamp.opengl.util.texture.Texture;
import de.enwaffel.randomutils.Properties;
import de.enwaffel.randomutils.file.FileOrPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GML {

    private static Properties properties;
    private static boolean initialized;
    private static Thread updateThread;
    private static Thread renderThread;
    private static int ups;
    private static int fps;
    public static int maxFPS = 60;
    protected static State currentState;

    protected static SoundSystem soundSystem;
    protected static RenderSystem renderSystem;

    // cache

    protected static final HashMap<FileOrPath, Sound> cache_sound = new HashMap<>();
    protected static final HashMap<FileOrPath, SpriteImage> cache_image = new HashMap<>();
    protected static final List<Camera> cameras = new ArrayList<>();

    public static void init(State initialState, String windowTitle, int windowWidth, int windowHeight) {
        properties = new Properties()
                .set("windowTitle", windowTitle)
                .set("windowWidth", windowWidth)
                .set("windowHeight", windowHeight);


        // services
        soundSystem = new SoundSystem();
        soundSystem.init(new Properties());
        renderSystem = new RenderSystem();
        renderSystem.init(properties);
        cameras.add(createCamera());

        run();
        initialized = true;
        switchState(initialState);
    }

    // sound

    public static Sound loadSound(FileOrPath fileOrPath) {
        check();
        Sound obj = checkCache(fileOrPath, cache_sound);
        if (obj == null) {
            obj = soundSystem.load(fileOrPath);
            cache_sound.put(fileOrPath, obj);
        }
        return obj;
    }


    // image
    public static SpriteImage loadImage(FileOrPath fileOrPath) {
        check();
        SpriteImage obj = checkCache(fileOrPath, cache_image);
        if (obj == null) {
            obj = load_image(fileOrPath);
            cache_image.put(fileOrPath, obj);
        }
        return obj;
    }

    private static SpriteImage load_image(FileOrPath fileOrPath) {
        UUID textureId = UUID.randomUUID();
        final Texture[] glTexture = new Texture[1];
        final boolean[] returned = new boolean[1];
        renderSystem.scheduleTexture(fileOrPath, texture -> {
            glTexture[0] = texture;
            returned[0] = true;
        });
        while (!returned[0]) { Thread.onSpinWait(); }
        return new SpriteImage(textureId, glTexture[0]);
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
        if (currentState != null) currentState.remove();
        for (Sound sound : SoundSystem.sounds) { SoundSystem.deleteSound(sound); }
        currentState = state;
        currentState.create();
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

    private static <T> T checkCache(Object obj, HashMap<?, T> cache) {
        for (Object o : cache.keySet()) {
            if (o.equals(obj)) return cache.get(o);
        }
        return null;
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
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                delta--;
                // update
                long last_time = System.nanoTime();
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
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                delta--;
                renderSystem.draw();
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
