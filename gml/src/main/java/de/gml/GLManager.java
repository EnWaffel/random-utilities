package de.gml;

import com.jogamp.nativewindow.WindowClosingProtocol;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.event.WindowListener;
import com.jogamp.newt.event.WindowUpdateEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;
import de.enwaffel.randomutils.Properties;
import de.enwaffel.randomutils.file.FileOrPath;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class GLManager implements ServiceBase, GLEventListener {

    private GLProfile profile;
    public static GLWindow window;
    private GLCapabilities windowCapabilities;
    private final HashMap<FileOrPath, callback001> requestedTextures = new HashMap<>();
    private final HashMap<BufferedImage, callback001> requestedTexturesB = new HashMap<>();
    private Properties properties;
    private int zoom = 1;

    protected GLManager() {
    }

    @Override
    public void init(Properties properties) {
        this.properties = properties;

        GLProfile.initSingleton();
        profile = GLProfile.get(GLProfile.GL2);

        windowCapabilities = new GLCapabilities(profile);
        window = GLWindow.create(windowCapabilities);
        window.setTitle(properties.get("windowTitle").s());
        window.setSize(properties.get("windowWidth").i(), properties.get("windowHeight").i());
        window.setPosition((Toolkit.getDefaultToolkit().getScreenSize().width - window.getWidth()) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - window.getHeight()) / 2);
        window.setDefaultCloseOperation(WindowClosingProtocol.WindowClosingMode.DISPOSE_ON_CLOSE);
        window.setUpdateFPSFrames(300, System.out);
        window.addGLEventListener(this);
        window.addWindowListener(new GLWindowListener());

        window.setVisible(true);
        window.requestFocus();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void disable() {
        window.setVisible(false);
    }

    protected void draw() {
        window.display();
    }

    protected void requestTexture(FileOrPath fileOrPath, callback001 callback) {
        requestedTextures.put(fileOrPath, callback);
    }

    protected void requestTextureB(BufferedImage image, callback001 callback) {
        requestedTexturesB.put(image, callback);
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 1);
        gl.glEnable(GL2.GL_TEXTURE_2D);
        //gl.glViewport(0, 0, properties.get("windowWidth").i(), properties.get("windowHeight").i());
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        try {
            for (Map.Entry<FileOrPath, callback001> set : requestedTextures.entrySet()) {
                BufferedImage b = ImageIO.read(set.getKey().getFile());
                set.getValue().call(AWTTextureIO.newTexture(profile, b, true), b);
                requestedTextures.remove(set.getKey());
            }

            for (Map.Entry<BufferedImage, callback001> set : requestedTexturesB.entrySet()) {
                BufferedImage b = set.getKey();
                set.getValue().call(AWTTextureIO.newTexture(profile, b, true), b);
                requestedTexturesB.remove(set.getKey());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (GML.currentState != null) {
            GL2 gl = glAutoDrawable.getGL().getGL2();

            gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

            for (Base member : GML.currentState.getMembers()) {
                if (member instanceof Sprite) {
                    Sprite sprite = (Sprite) member;
                    float x = sprite.getX();
                    float y = (sprite.getY());
                    float width = (sprite.getWidth() != 1 ? sprite.getWidth() : 2);
                    float height = (sprite.getHeight() != 1 ? sprite.getHeight() : 2);
                    if (sprite.getTexture() != null) {
                        x -= sprite.getTexture()._offsetX;
                        y -= sprite.getTexture()._offsetY;
                        width = sprite.getTexture().b.getWidth();
                        height = sprite.getTexture().b.getHeight();
                    }

                    gl.glBegin(GL2.GL_LINES);
                    gl.glVertex2f(x, y);
                    gl.glVertex2f(x, y + height);
                    gl.glVertex2f(x + width, y + height);
                    gl.glVertex2f(x + width, y);
                    gl.glEnd();

                    if (sprite.getTexture() == null) {
                        gl.glBegin(GL2.GL_QUADS);
                        gl.glVertex2f(x, y);
                        gl.glVertex2f(x, y + height);
                        gl.glVertex2f(x + width, y + height);
                        gl.glVertex2f(x + width, y);
                        gl.glEnd();
                    } else {
                        Texture texture = sprite.getTexture().glTexture;
                        TextureCoords texCoords = texture.getImageTexCoords();
                        texture.enable(gl);
                        texture.bind(gl);
                        //gl.glRotatef( 15, 0.0f, 1.0f, 0.0f );


                        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_NEAREST);
                        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_NEAREST);

                        gl.glBegin(GL2.GL_QUADS);

                        gl.glVertex2f(x, y);
                        gl.glTexCoord2f(texCoords.left(), texCoords.top());

                        gl.glVertex2f(x, y + height);
                        gl.glTexCoord2f(texCoords.right(), texCoords.top());

                        gl.glVertex2f(x + width, y + height);
                        gl.glTexCoord2f(texCoords.right(), texCoords.bottom());

                        gl.glVertex2f(x + width, y);
                        gl.glTexCoord2f(texCoords.left(), texCoords.bottom());

                        gl.glEnd();
                        texture.disable(gl);

                    }
                }
            }
        }
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height) {
        GL2 gl = glAutoDrawable.getGL().getGL2();

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        gl.glOrtho(-properties.get("windowWidth").i(), properties.get("windowWidth").i(), -properties.get("windowHeight").i(), properties.get("windowHeight").i(), -1, 1);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    protected static class GLWindowListener implements WindowListener {

        @Override
        public void windowResized(WindowEvent windowEvent) {

        }

        @Override
        public void windowMoved(WindowEvent windowEvent) {

        }

        @Override
        public void windowDestroyNotify(WindowEvent windowEvent) {

        }

        @Override
        public void windowDestroyed(WindowEvent windowEvent) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                    cancel();
                }
            }, 0, 1);
        }

        @Override
        public void windowGainedFocus(WindowEvent windowEvent) {
            if (GML.pauseOnLostFocus) GML.running = true;
            for (Sound sound : SoundSystem.sounds) if (sound.paused) sound.play();
        }

        @Override
        public void windowLostFocus(WindowEvent windowEvent) {
            if (GML.pauseOnLostFocus) GML.running = false;
            for (Sound sound : SoundSystem.sounds) if (sound.playing) sound.pause();
        }

        @Override
        public void windowRepaint(WindowUpdateEvent windowUpdateEvent) {

        }

    }

}
