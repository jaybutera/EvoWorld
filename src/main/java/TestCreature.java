import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class TestCreature extends Creature {
    public byte[] serialize () {
        // Can leave implementation empty for now
        return null;
    }

    public void action (float[] a) {
        // Needs implementation
    }

    public float[] observation () {
        // Needs implementation
        return null;
    }

    public void display (GL2 gl) {
        gl.glBegin (GL2.GL_LINES);//static field
        gl.glVertex2f(0.50f,-0.50f);
        gl.glVertex2f(-0.50f,0.50f);
        gl.glEnd();
    }
}
