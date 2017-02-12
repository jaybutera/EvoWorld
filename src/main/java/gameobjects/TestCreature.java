package gameobjects;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;

import java.util.ArrayList;

public class TestCreature extends Creature {
    public TestCreature(Body body, float scale, int id, World world) {
        super(body, scale, id, world);
    }

    public boolean action(float[] a) {
        if (a.length != 2) {
            System.out.println("Float array for action array is incorrect size");
            return false;
        }

        // Movement force vector
        Vec2 move = new Vec2(a[0], a[1]);

        // Deplete energy
        energy -= .1 * (move.length());

        // Forward vector
        Vec2 forward_vec = body.getWorldVector(new Vec2(0, 1));

        // apply force at index 0
        this.body.applyLinearImpulse(forward_vec.mul(500 * a[0]), body.getWorldPoint(new Vec2(-scale / 2f, 0)), true);
        // apply force at index 1
        this.body.applyLinearImpulse(forward_vec.mul(500 * a[1]), body.getWorldPoint(new Vec2(scale / 2f, 0)), true);

        // True means dead, false is still alive.
        // It's terrible and I hate it. I hope to change this soon.
        return true ? energy < 0f : false;
    }

    // Builds a flat buffer representation of observation and returns offset
    public CreatureObservation observation(ArrayList<Fixture> foundFixtures) {
        float[] b = {0f, 0f, 0f};
        Vec2 center = body.getPosition();

        // Accumulate strength of each chemical for all objects in smell range
        for (Fixture fixture : foundFixtures) {
            ChemicalComposition cc = (ChemicalComposition) fixture.getUserData();

            if (cc != null) {
                float dist = fixture.computeDistance(center, 0, new Vec2(0, 0));

                b[0] += cc.getProtein() * cc.getStrength() / dist;
                b[1] += cc.getStarch() * cc.getStrength() / dist;
                b[2] += cc.getFat() * cc.getStrength() / dist;
            }
        }

        return new CreatureObservation(b, id);
    }
}
