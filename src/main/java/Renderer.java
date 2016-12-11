import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
//import net.java.games.jogl.*;

import java.awt.*;

public class Renderer {
    public static void main(String[] args) {
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capab = new GLCapabilities(profile);

        final GLCanvas glCanvas = new GLCanvas(capab);
        //Frame frame = new Frame("hello world");
        //GLCanvas canvas = GLDrawableFactory.getFactory().createGLCanvas( new GLCapabilities() );
        //frame.add(canvas);

        BasicFrame basicframe = new BasicFrame( );// class which implement GLEventListener interface
        glCanvas.addGLEventListener( basicframe );
    }
}
