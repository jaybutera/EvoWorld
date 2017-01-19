import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import renderEngine.Renderer;

public class PetriWorld {
	private BodyDef bodyDef;
    private AABB worldAABB; // Boundaries for world
    private World world;
    private float timeStep = 1f/60f; // 60 frames per second
    private int num_bodies; // Number of dynamic bodies in the scene

    public Creature[] d_bodies; // List of bodies in world

    public PetriWorld (int nb) {
        num_bodies = nb;
        d_bodies = new Creature[num_bodies];
    }

    public void step  () {
        // Apply forces to dynamic bodies
        //d_bodies[0].body.applyForce( new Vec2(0,50), d_bodies[0].body.getWorldCenter() );
        //d_bodies[1].body.applyForce( new Vec2(0,-50), d_bodies[1].body.getWorldCenter() );
        
    	for(int x = 0; x < num_bodies; x++) {
    		int xDirection = (int)(Math.pow(-1, (int)(Math.random()*10))); // positive or negative direction
    		int yDirection = (int)(Math.pow(-1, (int)(Math.random()*10)));
        	float forces[] = {(float)/*Math.random()*/10*xDirection, (float)/*Math.random()*/10*yDirection};
        	
        	d_bodies[x].action(forces);
    	}
    	
    	// Perform a time step in the physics simulation
        world.step(timeStep, 3, 3);
    }

    public void create () {
        // Define world boundaries
        worldAABB = new AABB();
        worldAABB.lowerBound.set(new Vec2((float) 0.0, (float) 0.0));
        worldAABB.upperBound.set(new Vec2((float) 500.0, (float) 500.0));

        // Initialize world with no gravity
        Vec2 gravity = new Vec2((float) 0.0, (float) 0.0);
        world = new World(gravity);

        // Make a body definition for dynamic bodies
        bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.angle = 0;

        // Body Fixture gives a shape to a body.
        // In this case a box
        CircleShape circleShape = new CircleShape();
        circleShape.m_radius = 1;

        FixtureDef boxFixtureDef = new FixtureDef();
        boxFixtureDef.shape = circleShape;
        boxFixtureDef.density = 1;
        boxFixtureDef.friction = 0.1f;

        // Setup bodies
        for (int i = 0 ; i < num_bodies; i++) {
            // Place bodies into world spaced 10 at a time
            // along the y-coordinate
            bodyDef.position.set(200, i * 10);
            Body new_body = world.createBody(bodyDef);
            // Assign body definition to body
            new_body.createFixture(boxFixtureDef);

            d_bodies[i] = new TestCreature(new_body, 1f);
        }
    }
 }
