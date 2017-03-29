package gameobjects;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;

import java.util.ArrayList;

public class TestCreature extends Creature {
    private final int lag_time = 60;
    private int lag_counter;
    private boolean lag_started;
    private ArrayList<Vec2> lag_vel;
    private Vec2 last_vel = new Vec2(0,0);

    public TestCreature(Body body, CreatureManager manager, float scale, int id) {
        super(body, manager, scale, id);

        lag_vel = new ArrayList<>();
    }

    public void action(float[] a) {
        if (a.length != 2) {
            System.out.println("Float array for action array is incorrect size");
        }

        // Movement force vector
        Vec2 move = new Vec2(a[0], a[1]);

        // Deplete energy
        energy -= .02 * (move.length());
        energy -= .03;

        // Forward vector
        Vec2 forward_vec = body.getWorldVector(new Vec2(0, 1));

        //this.last_move = move;

        // apply force at index 0
        this.body.applyLinearImpulse(forward_vec.mul(a[0]), body.getWorldPoint(new Vec2(-scale / 2f, 0)), true);
        // apply force at index 1
        this.body.applyLinearImpulse(forward_vec.mul(a[1]), body.getWorldPoint(new Vec2(scale / 2f, 0)), true);

        if (energy < 0f)
            die();
    }

    // Builds a flat buffer representation of observation and returns offset
    public CreatureObservation observation(ArrayList<Fixture> foundFixtures) {
        float[] b = {0f, 0f, 0f};
        Vec2 center = body.getPosition();

        // Acceleration
        //---------------------------
        Vec2 vel = body.getLinearVelocity();


        // Windowing, remove oldest record once large enough
        lag_vel.add(vel);
        if (lag_vel.size() > lag_time) {
            lag_vel.remove(0);
        }

        float[] accel_obs = {0f,0f};
        //Vec2 last_vel = lag_vel.get( 0 );

        /*
        System.out.println("last vel: " + (last_vel.x));
        System.out.println("vel: " + (vel.x));
        System.out.println("-");
        */

        if (vel.x != 0f)
            accel_obs[0] = (body.getMass() * (vel.x - last_vel.x) / (1f/60f));
        if (vel.y != 0f)
            accel_obs[1] = (body.getMass() * (vel.y - last_vel.y) / (1f/60f));// / (1f/60f)

        last_vel.x = vel.x;
        last_vel.y = vel.y;

        //System.out.println("lag counter: " + lag_counter);

        /*
        float[] accel_obs = {
                1f/(body.getMass() * vel.x),// / (1f/60f),
                1f/(body.getMass() * vel.y)// / (1f/60f)
                //this.last_move.x, this.last_move.y
        };
        */

        float ang_accel_obs = ( body.getMass() * body.getAngularVelocity() );// / (1f/60f);

        // Smell
        //---------------------------
        // Accumulate strength of each chemical for all objects in smell range
        for (Fixture fixture : foundFixtures) {
            UserDataContainer udContainer = (UserDataContainer) fixture.getUserData();

            // Add to smell if object is food
            if (udContainer.entityType == EntityType.Food) {
                ChemicalComposition cc = ( (Food) udContainer.entity ).chem;

                float dist = fixture.computeDistance(center, 0, new Vec2(0, 0));

                b[0] += cc.getProtein() * cc.getStrength() / dist;
                b[1] += cc.getStarch() * cc.getStrength() / dist;
                b[2] += cc.getFat() * cc.getStrength() / dist;
            }
        }
        //System.out.println("");

        return new CreatureObservation(b, accel_obs, ang_accel_obs, id);
    }
}
