package de.gml;

import com.jogamp.openal.AL;
import com.jogamp.openal.ALFactory;
import com.jogamp.openal.util.ALut;
import de.enwaffel.randomutils.Properties;
import de.enwaffel.randomutils.file.FileOrPath;
import de.enwaffel.randomutils.file.FileUtil;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class SoundSystem implements ServiceBase {

    public static boolean remove_sound_data_on_end = false;
    protected static final List<Sound> sounds = new ArrayList<>();
    protected static AL al;
    private final float[] listenerPos = {0.0f, 0.0f, 0.0f};
    private final float[] listenerVel = {0.0f, 0.0f, 0.0f};
    private final float[] listenerOri = {0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f};

    protected SoundSystem() {
    }

    public Sound load(FileOrPath fileOrPath) {
        String type = FileUtil.getExtension(fileOrPath.getFile());
        return switch (type) {
            case "wav" -> load_wav(fileOrPath);
            case "ogg" -> load_ogg(fileOrPath);
            default -> throw new IllegalArgumentException("Invalid audio type: " + type);
        };
    }

    public Sound load_wav(FileOrPath fileOrPath) {
        int[] buffer = new int[1];
        int[] source = new int[1];
        float[] sourcePos = {0.0f, 0.0f, 0.0f};
        float[] sourceVel = {0.0f, 0.0f, 0.0f};
        int[] format = new int[1];
        int[] size = new int[1];
        ByteBuffer[] data = new ByteBuffer[1];
        int[] freq = new int[1];
        int[] loop = new int[1];

        al.alGenBuffers(1, buffer, 0);

        ALut.alutLoadWAVFile(fileOrPath.getPath(), format, data, size, freq, loop);
        al.alBufferData(buffer[0], format[0], data[0], size[0], freq[0]);

        al.alGenSources(1, source, 0);

        al.alSourcei(source[0], AL.AL_BUFFER, buffer[0]);
        al.alSourcef(source[0], AL.AL_PITCH, 1.0f);
        al.alSourcef(source[0], AL.AL_GAIN, 1.0f);
        al.alSourcefv(source[0], AL.AL_POSITION, sourcePos, 0);
        al.alSourcefv(source[0], AL.AL_VELOCITY, sourceVel, 0);
        al.alSourcei(source[0], AL.AL_LOOPING, loop[0]);

        Sound sound = new Sound(buffer[0], source[0], sourcePos, sourceVel, format[0], size[0], freq[0], loop[0]);
        sounds.add(sound);
        return sound;
    }

    private Sound load_ogg(FileOrPath fileOrPath) {
        return null;
    }

    protected static void deleteSound(Sound sound) {
        sound.stop();
        IntBuffer i = IntBuffer.allocate(1);
        al.alDeleteBuffers(sound.buffer, i);
        al.alDeleteSources(sound.source, i);
    }

    @Override
    public void init(Properties properties) {
        al = ALFactory.getAL();
        ALut.alutInit();

        al.alListenerfv(AL.AL_POSITION,	listenerPos, 0);
        al.alListenerfv(AL.AL_VELOCITY,    listenerVel, 0);
        al.alListenerfv(AL.AL_ORIENTATION, listenerOri, 0);
    }

    @Override
    public void update(float delta) {
        for (int i = 0;i < sounds.size();i++) {
            Sound sound = sounds.get(i);
            if (sound != null) {
                sound.update(delta);
            }
        }
    }

    @Override
    public void disable() {
        ALut.alutExit();
    }

}
