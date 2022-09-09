package de.gml;

import com.jogamp.opengl.util.texture.Texture;

import java.util.UUID;

public class SpriteImage {

    protected final UUID textureId;
    protected final Texture glTexture;

    protected SpriteImage(UUID textureId, Texture glTexture) {
        this.textureId = textureId;
        this.glTexture = glTexture;
    }

    protected SpriteImage() throws IllegalAccessException {
        throw new IllegalAccessException("illegal constructor accessed");
    }

    public float getWidth() {
        return glTexture.getImageWidth();
    }

    public float getHeight() {
        return glTexture.getImageHeight();
    }

}
