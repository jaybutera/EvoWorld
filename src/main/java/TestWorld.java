import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;

public class TestWorld {
    private BodyDef bodyDef;
    private AABB worldAABB; // Boundaries for world
    private World world;
    private float timeStep = 1f/60f; // 60 frames per second
    private int num_bodies; // Number of dynamic bodies in the scene
    private Renderer renderer;

    public Body[] d_bodies; // List of bodies in world

    public TestWorld (int nb) {
        num_bodies = nb;
        d_bodies = new Body[num_bodies];
        renderer = new Renderer();
    }

    public void step  () {
        // Apply forces to dynamic bodies (collied them)
        d_bodies[0].applyForce( new Vec2(0,50), d_bodies[0].getWorldCenter() );
        d_bodies[1].applyForce( new Vec2(0,-50), d_bodies[1].getWorldCenter() );

        // Perform a time step in the physics simulation
        world.step(timeStep, 3, 3);
    }

    public void create () {
        // Define world boundaries
        worldAABB = new AABB();
        worldAABB.lowerBound.set(new Vec2((float) -100.0, (float) -100.0));  
        worldAABB.upperBound.set(new Vec2((float) 100.0, (float) 100.0));

        // Initialize world with no gravity
        Vec2 gravity = new Vec2((float) 0.0, (float) 0.0);
        world = new World(gravity);
        world.setDebugDraw(renderer);
        renderer.setFlags(DebugDraw.e_shapeBit);

        // Make a body definition for dynamic bodies
        bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.angle = 0;

        // Body Fixture gives a shape to a body.
        // In this case a box
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(1,1);

        FixtureDef boxFixtureDef = new FixtureDef();
        boxFixtureDef.shape = boxShape;
        boxFixtureDef.density = 1;
        boxFixtureDef.friction = 0.1f;

        // Setup bodies
        for (int i = 0 ; i < num_bodies; i++) {
            // Place bodies into world spaced 10 at a time
            // along the y-coordinate
            bodyDef.position.set(0, i * 10);
            d_bodies[i] = world.createBody(bodyDef);

            // Attach body fixture to body
            d_bodies[i].createFixture(boxFixtureDef);
        }
    }
 }
