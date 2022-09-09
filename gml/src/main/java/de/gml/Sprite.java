package de.gml;

import java.awt.Color;

public class Sprite implements Base {

    private Camera camera = GML.cameras.get(0);
    private float x;
    private float y;
    private float width;
    private float height;
    private SpriteImage texture;
    private Color color;

    public Camera getCamera() {
        return camera;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public SpriteImage getTexture() {
        return texture;
    }

    public Color getColor() {
        return color;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setTexture(SpriteImage texture) {
        this.texture = texture;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void create() {}

    @Override
    public void update(float delta) {

    }

    @Override
    public void remove() {}

}
