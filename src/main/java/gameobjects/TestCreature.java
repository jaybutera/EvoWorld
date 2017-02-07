package gameobjects;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

public class TestCreature extends Creature {
    public TestCreature (Body body, float scale, int id, World world) {
        super(body, scale, id, world);
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

        // apply force at index 0
        this.body.applyLinearImpulse(forward_vec.mul(500*a[0]), body.getWorldPoint( new Vec2(-scale/2f,0) ), true);
        // apply force at index 1
        this.body.applyLinearImpulse(forward_vec.mul(500*a[1]), body.getWorldPoint( new Vec2(scale/2f, 0) ), true);
    }

    public float[] observation () {
        // Needs implementation
        //return new byte[65];
        //byte[] b = new byte[65];
        float[] b = new float[3];
        b[0] = .2f;
        b[1] = .4f;
        b[2] = .6f;

        /*
        Vec2 center = body.getPosition();
        AABB smellRange = new AABB(center.add( new Vec2(-15f, -15f) ), center.add( new Vec2(15f, 15f) ));

        OdorQueryCallback aabbCallback = new OdorQueryCallback();
        world.queryAABB(aabbCallback, smellRange);
        */

        //System.out.println(aabbCallback.foundChems);

        return b;
    }
}
