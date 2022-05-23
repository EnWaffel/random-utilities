package de.enwaffel.randomutils.render;

import de.enwaffel.randomutils.Properties;
import de.enwaffel.randomutils.Property;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Renderer extends Thread {

    private final List<RenderAdapter> adapters = new ArrayList<>();
    private final Properties properties;
    private final Component obj;

    private boolean running;
    private int timeSince;

    public Renderer(Component obj, Properties properties) {
        this.obj = obj;
        this.properties = properties;
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
            render();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void render() throws Exception {

        timeSince++;
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
        return properties;
    }

}
