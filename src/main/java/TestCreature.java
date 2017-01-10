import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
// import org.jbox2d.p5.Physics;
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
        // this way gets random movement but doesn't get thrusters on both sides
        this.body.applyForceToCenter(new Vec2 (a[0], a[1]));
        
        // apply force at index 0
        //Physics.applyForce(this, )
        // apply force at index 1
    }

    public float[] observation () {
        // Needs implementation
        return null;
    }
}
