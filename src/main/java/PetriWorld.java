import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import renderEngine.Renderer;

import java.util.Random;

public class PetriWorld {
	private BodyDef bodyDef;
    private AABB worldAABB; // Boundaries for world
    private World world;
    private float timeStep = 1f/60f; // 60 frames per second
    private int num_bodies; // Number of dynamic bodies in the scene
    private int num_food = 10;

    public Creature[] creatures;
    public Food[] food;

    public PetriWorld (int nb) {
        num_bodies = nb;
        creatures = new Creature[num_bodies];
        food = new Food[num_food];
    }

    public void step  () {
        // Apply forces to dynamic bodies
        //d_bodies[0].body.applyForce( new Vec2(0,50), d_bodies[0].body.getWorldCenter() );
        //d_bodies[1].body.applyForce( new Vec2(0,-50), d_bodies[1].body.getWorldCenter() );
        
    	for(int x = 0; x < num_bodies; x++) {
    		int xDirection = (int)(Math.pow(-1, (int)100)); // positive or negative direction
    		int yDirection = (int)(Math.pow(-1, (int)(100)));
        	float forces[] = {(float)/*Math.random()*/xDirection, (float)/*Math.random()*/yDirection};
        	
        	creatures[x].action(forces);
    	}
    	
    	// Perform a time step in the physics simulation
        world.step(timeStep, 7, 7);
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

        // Body Fixture gives a shape to a body
        // ------------------------------------

        // Circle shape for creatures
        CircleShape circleShape = new CircleShape();
        circleShape.m_radius = 30;
        // Box for food
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(10,10);

        // ------------------------------------

        FixtureDef circFixtureDef = new FixtureDef();
        circFixtureDef.shape = circleShape;
        circFixtureDef.density = 1;
        circFixtureDef.friction = 0.1f;

        FixtureDef boxFixtureDef = new FixtureDef();
        boxFixtureDef.shape = circleShape;
        boxFixtureDef.density = 1;
        boxFixtureDef.friction = 0.5f;

        // Setup bodies
        Random r = new Random();
        for (int i = 0 ; i < num_bodies; i++) {
            // Place bodies into world spaced 10 at a time
            // along the y-coordinate
            bodyDef.position.set(200, i * 10);
            Body new_body = world.createBody(bodyDef);
            // Assign body definition to body
            new_body.createFixture(circFixtureDef);

            creatures[i] = new TestCreature(new_body, 30f);
        }
        for (int i = 0; i < num_food; i++) {
            // Make food
            bodyDef.position.set(r.nextInt(500), r.nextInt(500));
            Body new_body = world.createBody(bodyDef);
            // Assign body definition to body
            new_body.createFixture(boxFixtureDef);

            food[i] = new Food(new_body, 10f);
        }
    }
 }
