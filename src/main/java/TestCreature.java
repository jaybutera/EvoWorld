import org.jbox2d.dynamics.Body;
import toolbox.Vector3f;

public class TestCreature extends Creature {
    public TestCreature (Body body, float scale) {
        super(body, scale);
    }

    public void action (float[] a) {
        // Needs implementation
    }

    public float[] observation () {
        // Needs implementation
        return null;
    }
}
