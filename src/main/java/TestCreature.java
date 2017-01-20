import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

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
        //this.body.applyForceToCenter(new Vec2 (a[0], a[1]));

        // Movement force vector
        Vec2 move = new Vec2(a[0], a[1]);

        // Forward vector
        Vec2 forward_vec = body.getWorldVector( new Vec2(0,1) );

        System.out.println(move.toString());
        // apply force at index 0
        this.body.applyLinearImpulse(forward_vec.mul(a[0]), body.getWorldPoint( new Vec2(0, -1f) ), true);
        // apply force at index 1
        this.body.applyLinearImpulse(forward_vec.mul(a[1]), body.getWorldPoint( new Vec2(0, 1f) ), true);
    }

    public float[] observation () {
        // Needs implementation
        return null;
    }
}
