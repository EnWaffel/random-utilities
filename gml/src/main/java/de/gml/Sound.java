package de.gml;

import com.jogamp.openal.AL;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Sound implements Base {

    protected final int buffer;
    protected final int source;
    protected final float[] sourcePos;
    protected final float[] sourceVel;
    protected final int format;
    protected final int size;
    protected final int freq;
    protected final int loop;
    protected float lengthInSamples;
    protected boolean playing;
    protected boolean paused;
    protected final FloatBuffer sample_offset = FloatBuffer.allocate(1);

    protected Sound(int buffer, int source, float[] sourcePos, float[] sourceVel, int format, int size, int freq, int loop) {
        this.buffer = buffer;
        this.source = source;
        this.sourcePos = sourcePos;
        this.sourceVel = sourceVel;
        this.format = format;
        this.size = size;
        this.freq = freq;
        this.loop = loop;

        IntBuffer sizeInBytes = IntBuffer.allocate(1);
        IntBuffer channels = IntBuffer.allocate(1);
        IntBuffer bits = IntBuffer.allocate(1);
        SoundSystem.al.alGetBufferi(buffer, AL.AL_SIZE, sizeInBytes);
        SoundSystem.al.alGetBufferi(buffer, AL.AL_CHANNELS, channels);
        SoundSystem.al.alGetBufferi(buffer, AL.AL_BITS, bits);
        lengthInSamples = sizeInBytes.get(0) * 8F / (channels.get(0) * bits.get(0));
    }

    public void play() {
        SoundSystem.al.alSourcePlay(source);
        playing = true;
        paused = false;
    }

    public void pause() {
        SoundSystem.al.alSourcePause(source);
        playing = false;
        paused = true;
    }

    public void stop() {
        SoundSystem.al.alSourceStop(source);
        playing = false;
    }

    public float getPosition() {
        return (sample_offset.get(0) / freq) * 1000;
    }

    public float getLength() {
        return (lengthInSamples / freq) * 1000F;
    }

    @Override
    public void create() {}

    @Override
    public void update(float delta) {
        SoundSystem.al.alGetSourcef(source, AL.AL_SAMPLE_OFFSET, sample_offset);
        if (playing && getPosition() == 0F) if (SoundSystem.remove_sound_data_on_end) SoundSystem.deleteSound(this);
    }

    @Override
    public void destroy() {

    }

}
