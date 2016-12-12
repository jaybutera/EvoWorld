import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.JFrame;
import java.awt.*;

public class BasicFrame implements GLEventListener {
    // Initiates OpenGL rendering
    public void display(GLAutoDrawable drawable) {
        // method body
    }

    // Release all OpenGL resources in the context
    public void dispose(GLAutoDrawable drawable) {
        drawable.destroy();
    }

    // Called after context initialized
    public void init(GLAutoDrawable drawable) {
        // method body
    }

    // Called after window transformations (position/size)
    public void reshape(GLAutoDrawable drawable, int arg1, int arg2, int arg3, int arg4) {
        // method body
    }

    public static void main(String[] args) {

        // Use capabilities OpenGL 2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        BasicFrame b = new BasicFrame();
        glcanvas.addGLEventListener(b);
        glcanvas.setSize(400, 400);

        //creating frame
        final JFrame frame = new JFrame (" Basic Frame");

        // Add canvas to frame
        frame.getContentPane().add(glcanvas);
        // Make frame full screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setVisible(true);
    }

}