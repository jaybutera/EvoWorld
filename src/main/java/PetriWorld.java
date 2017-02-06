import gameobjects.Creature;
import gameobjects.CreatureFactory;
import gameobjects.Food;
import gameobjects.TestCreature;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;

import java.util.Random;

public class PetriWorld {
	private BodyDef bodyDef;
    private AABB worldAABB; // Boundaries for world
    private World world;
    private float timeStep = 1f/60f; // 60 frames per second
    private int num_bodies; // Number of dynamic bodies in the scene
    private int num_food = 30;

    public Creature[] creatures;
    public Food[] food;

    public PetriWorld (int[] creature_ids) {
        num_bodies = creature_ids.length;
        creatures = new Creature[num_bodies];
        food = new Food[num_food];

        // Define world boundaries
        worldAABB = new AABB();
        worldAABB.lowerBound.set(new Vec2((float) 0.0, (float) 0.0));
        worldAABB.upperBound.set(new Vec2((float) 500.0, (float) 500.0));

        // Initialize world with no gravity
        Vec2 gravity = new Vec2((float) 0.0, (float) 0.0);
        world = new World(gravity);

        initBoundaries();
        initEntities(creature_ids);
    }

    public void step  () {
        /*
    	for(int x = 0; x < num_bodies; x++) {
    		int left = (int)(500); // positive or negative direction
    		int right = (int)((500));
        	float forces[] = {(float)left, (float)right};

        	creatures[x].action(forces);
    	}
    	*/

        // Perform a time step in the physics simulation
        world.step(timeStep, 7, 7);
    }

    private void initEntities (int[] creature_ids) {
        // Box for food
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(5,5);

        // ------------------------------------

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.angle = 0;

        FixtureDef boxFixtureDef = new FixtureDef();
        boxFixtureDef.shape = boxShape;
        boxFixtureDef.density = 3;
        boxFixtureDef.friction = 1.5f;

        // Setup creatures
        Random r = new Random();
        for (int i = 0 ; i < num_bodies; i++) {
            creatures[i] = CreatureFactory.getCreature(creature_ids[i],world);
        }
        for (int i = 0; i < num_food; i++) {
            // Make food
            bodyDef.position.set(r.nextInt(500), r.nextInt(500));
            Body new_body = world.createBody(bodyDef);
            // Assign body definition to body
            new_body.createFixture(boxFixtureDef);

            food[i] = new Food(new_body, 10f, i);
            boxFixtureDef.setUserData( food[i].chem );
        }
    }

    private void initBoundaries () {
        // Set up boundary walls
        PolygonShape horiz_wall_shape = new PolygonShape();
        horiz_wall_shape.setAsBox(500,10);
        FixtureDef horiz_wall_fix = new FixtureDef();
        horiz_wall_fix.shape = horiz_wall_shape;

        PolygonShape vert_wall_shape = new PolygonShape();
        vert_wall_shape.setAsBox(10,500);
        FixtureDef vert_wall_fix = new FixtureDef();
        vert_wall_fix.shape = vert_wall_shape;

        BodyDef wallDef = new BodyDef();
        wallDef.type = BodyType.STATIC;

        // Top wall
        wallDef.position.set(0,0);
        Body top_wall = world.createBody(wallDef);
        top_wall.createFixture(horiz_wall_fix);

        // Bottom wall
        wallDef.position.set(0,500);
        Body bot_wall = world.createBody(wallDef);
        bot_wall.createFixture(horiz_wall_fix);

        // Left wall
        wallDef.position.set(0,0);
        Body left_wall = world.createBody(wallDef);
        left_wall.createFixture(vert_wall_fix);

        // Right wall
        wallDef.position.set(500,0);
        Body right_wall = world.createBody(wallDef);
        right_wall.createFixture(vert_wall_fix);
    }
 }
