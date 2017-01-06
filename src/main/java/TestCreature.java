import org.jbox2d.dynamics.Body;
import toolbox.Vector3f;

public class TestCreature extends Creature {
    public TestCreature (Body body, float scale) {
        super(body, scale);
    }

    public void action (float[] a) {
        if(a.length != 2) {
        	System.out.println("Float array for action array is incorrect size");
        	return;
        }
        // apply force at index 0
        // apply force at index 1
    }

    public float[] observation () {
        // Needs implementation
        return null;
    }
}
