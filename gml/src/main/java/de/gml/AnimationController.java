package de.gml;

import java.util.HashMap;

public class AnimationController implements Base {

    private final Sprite parent;
    private AnimationData currentAnimation;

    private int _fps_ms;
    private int _waited;
    private int _index;

    private final HashMap<String, AnimationData> animations = new HashMap<>();

    protected AnimationController(Sprite parent) {
        this.parent = parent;
    }

    public void load(LoadedAnimation loader) {
        animations.putAll(loader.getAnimations());
    }

    public void play(String name) {
        if (animations.containsKey(name)) {
            currentAnimation = animations.get(name);
            _fps_ms = 1000 / currentAnimation.getFPS();
            System.out.println(_fps_ms);
        }
    }

    @Override
    public void create() {}

    @Override
    public void update(float delta) {
        if (currentAnimation != null) {
            if (_waited >= _fps_ms) {
                _waited = 0;
                parent.setTexture(currentAnimation.getFrames().get(_index));
                _index++;
                if (_index >= currentAnimation.getFrames().size()) _index = 0;
            }
            _waited++;
        }
    }

    @Override
    public void destroy() {}

}
