import gameobjects.*;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.*;
import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class PetriWorld {
    private AABB worldAABB; // Boundaries for world
    private World world;
    private float timeStep = 1f/60f; // 60 frames per second
    private int num_bodies; // Number of dynamic bodies in the scene
    private int num_food = 20;
    private FitnessRecords fitnessRecords; // Used to track dead creatures and their fitness scores
    private CreatureFactory creatureFactory;
    private long local_iter;

    final public int worldSize = 1000;

    // Queue all deaths each frame for garbage collection
    private ArrayList<Creature> dead_creatures;

    public Creature[] creatures;
    public FoodManager foodManager;

    public PetriWorld (int[] creature_ids) {
        num_bodies     = creature_ids.length;
        creatures      = new Creature[num_bodies];
        //food           = new Food[num_food];
        dead_creatures = new ArrayList<>();
        fitnessRecords = new FitnessRecords();
        foodManager    = new FoodManager();
        local_iter     = 0;

        // Define world boundaries
        worldAABB = new AABB();
        worldAABB.lowerBound.set(new Vec2((float) 0.0, (float) 0.0));
        worldAABB.upperBound.set(new Vec2(worldSize, worldSize));

        // Initialize world with no gravity
        Vec2 gravity = new Vec2((float) 0.0, (float) 0.0);
        world = new World(gravity);

        creatureFactory = new CreatureFactory(0, world);

        initBoundaries();
        initEntities(creature_ids);

        // Setup contact listeners
        MouthSensorCallback mouthSensorContactListener = new MouthSensorCallback();
        world.setContactListener(mouthSensorContactListener);
    }

    public void step () {
        // Clean out dead creatures
        removeDeadCreatures();

        // Food management
        for (Food f : foodManager.foodToRemove) {
            world.destroyBody(f.body);
        }
        foodManager.clearFood();

        // Perform a time step in the physics simulation
        world.step(timeStep, 7, 7);
        local_iter++;
    }

    public void applyActions(HashMap<Integer, float[]> actions) {
        for (Creature c : creatures ) {

            /*
            System.out.print("Action [" + c.getId() + "]: ");
            for (int i = 0; i < actions.get(c.getId()).length; i++)
                System.out.print(" | " + actions.get(c.getId())[i]);
            System.out.println("");
            */

            if ( c.action( actions.get(c.getId()) ) == true ) // true means creature died
                dead_creatures.add(c);
        }
    }

    public CreatureObservation[] getObservations () {
        CreatureObservation[] creatureObservations = new CreatureObservation[ creatures.length ];

        Vec2 center;

        // Construct creature observations vector
        for (int i = 0; i < creatures.length; i++) {

            // Setup AABB query callback
            center = creatures[i].getPosition();
            AABB smellRange = new AABB(center.add(new Vec2(-300f, -300f)), center.add(new Vec2(300f, 300f)));
            OdorQueryCallback aabbCallback = new OdorQueryCallback();
            world.queryAABB(aabbCallback, smellRange);

            creatureObservations[i] = creatures[i].observation(aabbCallback.foundFixtures);
        }

        return creatureObservations;
    }

    private void removeDeadCreatures () {
        // Record deaths
        for (Creature c : dead_creatures) {
            Integer fit = (int)local_iter;
            fitnessRecords.addRecord(c, fit.floatValue());
        }

        // Prune creatures
        Creature[] tmp_creatures = new Creature[creatures.length - dead_creatures.size()];
        int j = 0;
        for (int i = 0; i < creatures.length; i++) {
            if ( !dead_creatures.contains(creatures[i]) && j < tmp_creatures.length ) {
                tmp_creatures[j++] = creatures[i];
            }
            else {
                // Destroy creature body
                //world.destroyBody( creatures[i].body );
            }
        }

        creatures = tmp_creatures;
        // Reset dead creature count
        dead_creatures = new ArrayList<>();
    }

    private void initEntities (int[] creature_ids) {
        Food[] food = new Food[num_food];

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
        boxFixtureDef.filter.categoryBits = EntityType.Food.getCategoryBit();

        // Setup creatures
        Random r = new Random();
        for (int i = 0 ; i < num_bodies; i++) {
            creatures[i] = creatureFactory.getTestCreature(creature_ids[i]);
        }
        for (int i = 0; i < num_food; i++) {
            // Make food
            bodyDef.position.set(r.nextInt(worldSize), r.nextInt(worldSize));
            Body new_body = world.createBody(bodyDef);
            // Assign body definition to body
            Fixture new_body_fix = new_body.createFixture(boxFixtureDef);

            food[i] = new Food(new_body, foodManager, 10f, i);
            new_body_fix.setUserData( new UserDataContainer(food[i].getEntityType(), food[i]) );
        }

        foodManager.initFood(food);
    }

    private void initBoundaries () {
        // Set up boundary walls
        PolygonShape horiz_wall_shape = new PolygonShape();
        horiz_wall_shape.setAsBox(worldSize,10);
        FixtureDef horiz_wall_fix = new FixtureDef();
        horiz_wall_fix.shape = horiz_wall_shape;

        PolygonShape vert_wall_shape = new PolygonShape();
        vert_wall_shape.setAsBox(10,worldSize);
        FixtureDef vert_wall_fix = new FixtureDef();
        vert_wall_fix.shape = vert_wall_shape;

        BodyDef wallDef = new BodyDef();
        wallDef.type = BodyType.STATIC;

        // Top wall
        wallDef.position.set(0,0);
        Body top_wall = world.createBody(wallDef);
        Fixture twall_fix = top_wall.createFixture(horiz_wall_fix);
        twall_fix.setUserData( new UserDataContainer(EntityType.Wall, null) );

        // Bottom wall
        wallDef.position.set(0,worldSize);
        Body bot_wall = world.createBody(wallDef);
        Fixture bwall_fix = bot_wall.createFixture(horiz_wall_fix);
        bwall_fix.setUserData( new UserDataContainer(EntityType.Wall, null) );

        // Left wall
        wallDef.position.set(0,0);
        Body left_wall = world.createBody(wallDef);
        Fixture lwall_fix = left_wall.createFixture(vert_wall_fix);
        lwall_fix.setUserData( new UserDataContainer(EntityType.Wall, null) );

        // Right wall
        wallDef.position.set(worldSize,0);
        Body right_wall = world.createBody(wallDef);
        Fixture rwall_fix = right_wall.createFixture(vert_wall_fix);
        rwall_fix.setUserData( new UserDataContainer(EntityType.Wall, null) );
    }

    public FitnessRecords getFitnessRecords () {
        return fitnessRecords;
    }
 }
