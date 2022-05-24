package de.enwaffel.randomutils.render;

import de.enwaffel.randomutils.Properties;
import de.enwaffel.randomutils.Property;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Renderer extends Thread {

    private final List<RenderAdapter> adapters = new ArrayList<>();
    private final Properties properties;
    private final Window obj;

    private BufferStrategy buffer;
    private int curFps;

    private boolean running;
    private int msElapsed;

    public Renderer(Window obj, Properties properties) {
        this.obj = obj;
        this.properties = properties;
    }

    public Renderer addAdapter(RenderAdapter adapter) {
        adapters.add(adapter);
        return this;
    }

    public Renderer removeAdapter(RenderAdapter adapter) {
        adapters.remove(adapter);
        return this;
    }

    public void begin() {
        running = true;
        if (properties.get("threaded").b()) {
            start();
        }
    }

    @Override
    public void run() {
        try {
            updateRender();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    long lastTime = System.nanoTime();
    long timer = System.currentTimeMillis();
    final double ns = 1000000000.0 / 240;
    double delta = 0;
    int frames = 0;

    public void updateRender() {

        synchronized (this) {
                if (running) {
                    long now = System.nanoTime();
                    delta += (now - lastTime) / ns;
                    lastTime = now;
                    while (delta >= 1) {
                        delta--;
                        preRender();
                        frames++;
                    }
                    if (System.currentTimeMillis() - timer > 1000) {
                        timer += 1000;
                        curFps = frames;
                        frames = 0;
                    }
                }
            }
        msElapsed++;
    }

    private void preRender() {
        try {
            do {
                do {
                    if (obj.getBufferStrategy() == null) {
                        obj.createBufferStrategy(2);
                        buffer = obj.getBufferStrategy();
                        return;
                    }
                    Graphics2D graphics = (Graphics2D) buffer.getDrawGraphics();
                    if (properties.get("antialiasing").b()) {
                        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    } else {
                        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
                    }
                    if (properties.get("text_antialiasing").b()) {
                        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                    } else {
                        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
                    }

                    for (int i = 0;i < adapters.size();i++) {
                        RenderAdapter adapter = adapters.get(i);
                        if (adapter != null) {
                            adapter.render(graphics, new Properties().set("elapsed", new Property(msElapsed)).set("fps", new Property(curFps)));
                        }
                    }

                    graphics.dispose();
                } while (buffer.contentsRestored());

                buffer.show();

            } while (buffer.contentsLost());
        } catch (Exception e) {
            if (Objects.equals(e.getMessage(), "Buffers have not been created")) {
                obj.createBufferStrategy(3);
                buffer = obj.getBufferStrategy();
            } else {
                e.printStackTrace();
            }
        }
    }

    public List<RenderAdapter> getAdapters() {
        return adapters;
    }

    public Component getObject() {
        return obj;
    }

    public Properties getProperties() {
        return properties;
    }

    public static Properties defaultProperties() {
        Properties properties = new Properties();
        properties.set("threaded", new Property(true));
        properties.set("fps", new Property(60));
        properties.set("antialiasing", new Property(true));
        properties.set("text_antialiasing", new Property(true));
        return properties;
    }

}
