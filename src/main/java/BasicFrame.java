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

        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        BasicFrame b = new BasicFrame();
        glcanvas.addGLEventListener(b);
        glcanvas.setSize(400, 400);

        //creating frame
        final Frame frame = new Frame (" Basic Frame");

        //adding canvas to frame
        frame.add(glcanvas);
        frame.setSize( 640, 480 );
        frame.setVisible(true);
    }

}