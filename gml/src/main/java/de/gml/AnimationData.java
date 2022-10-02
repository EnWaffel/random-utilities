package de.gml;

import java.util.List;

public class AnimationData {

    private final List<TextureImage> frames;
    private final int fps;

    protected AnimationData(List<TextureImage> frames, int fps) {
        this.frames = frames;
        this.fps = fps == 0 ? 24 : fps;
    }

    public List<TextureImage> getFrames() {
        return frames;
    }

    public int getFPS() {
        return fps;
    }

}
