package de.gml;

import com.jogamp.nativewindow.WindowClosingProtocol;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import de.enwaffel.randomutils.Properties;
import de.enwaffel.randomutils.file.FileOrPath;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

public class RenderSystem implements ServiceBase, GLEventListener {

    private GLProfile profile = GLProfile.get(GLProfile.GL2);
    public static GLWindow window;
    private GLCapabilities windowCapabilities;
    private HashMap<FileOrPath, callback001> scheduledTextures = new HashMap<>();

    protected RenderSystem() {}

    @Override
    public void init(Properties properties) {
        windowCapabilities = new GLCapabilities(profile);
        window = GLWindow.create(windowCapabilities);
        window.setTitle(properties.get("windowTitle").s());
        window.setSize(properties.get("windowWidth").i(), properties.get("windowHeight").i());
        window.setPosition((Toolkit.getDefaultToolkit().getScreenSize().width - window.getWidth()) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - window.getHeight()) / 2);
        window.setDefaultCloseOperation(WindowClosingProtocol.WindowClosingMode.DISPOSE_ON_CLOSE);
        window.setUpdateFPSFrames(100, System.out);
        window.addGLEventListener(this);
        window.setVisible(true);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void disable() {

    }

    protected void draw() {
        window.display();
    }

    protected void scheduleTexture(FileOrPath fileOrPath, callback001 callback) {
        scheduledTextures.put(fileOrPath, callback);
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(0, 0, 0, 0);
        gl.glClearDepth(1);

        //gl.glViewport(0, 0, 1280, 720);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glTranslated(-1, 1, 0);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity() ;
        gl.glHint(GL2.GL_LINE_SMOOTH_HINT, GL2.GL_NICEST);
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        try {
            for (Map.Entry<FileOrPath, callback001> set : scheduledTextures.entrySet()) {
                set.getValue().call(TextureIO.newTexture(set.getKey().getFile(), true));
                scheduledTextures.remove(set.getKey());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (GML.currentState != null) {
            GL2 gl = glAutoDrawable.getGL().getGL2();
            gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

            for (Base member : GML.currentState.getMembers()) {
                if (member instanceof Sprite) {
                    Sprite sprite = (Sprite) member;
                    float x = sprite.getX() / 1000F;
                    float y = sprite.getY() / 1000F;
                    float width = (sprite.getWidth() != 1 ? sprite.getWidth() : 2) / 1000F;
                    float height = (sprite.getHeight() != 1 ? sprite.getHeight() : 2) / 1000F;
                    GML.print(x, y, width, height);

                    if (sprite.getTexture() == null) {
                        gl.glBegin(GL2.GL_QUADS);
                        gl.glVertex2f(x, -y);
                        gl.glVertex2f(x + width, -y);
                        gl.glVertex2f(x + width, -y - height);
                        gl.glVertex2f(x, -y - height);
                        gl.glEnd();
                    } else {
                        Texture texture = sprite.getTexture().glTexture;
                        texture.enable(gl);
                        texture.bind(gl);
                        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
                        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);

                        gl.glBegin(GL2.GL_QUADS);
                        gl.glTexCoord2d(0, 0);
                        gl.glVertex2d(x, y);
                        gl.glTexCoord2d(1, 0);
                        gl.glVertex2d(x + width, y);
                        gl.glTexCoord2d(1, 1);
                        gl.glVertex2d(x + width, y + height);
                        gl.glTexCoord2d(0, 1);
                        gl.glVertex2d(x, y + height);
                        gl.glEnd();
                        gl.glFlush();
                        texture.disable(gl);
                    }
                }
            }
/*


 */
        }
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

}
