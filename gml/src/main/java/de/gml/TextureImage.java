package de.gml;

import com.jogamp.opengl.util.texture.Texture;
import java.awt.image.BufferedImage;

public class TextureImage extends Resource {

    protected final Texture glTexture;
    protected final BufferedImage b;
    protected String _p;
    protected float _offsetX;
    protected float _offsetY;
    protected float _offsetW;
    protected float _offsetH;

    protected TextureImage(Texture glTexture, BufferedImage b) {
        this.glTexture = glTexture;
        this.b = b;
    }

    protected TextureImage() throws IllegalAccessException {
        throw new IllegalAccessException("Illegal constructor accessed");
    }

    public float getWidth() {
        return glTexture.getImageWidth();
    }

    public float getHeight() {
        return glTexture.getImageHeight();
    }

}
