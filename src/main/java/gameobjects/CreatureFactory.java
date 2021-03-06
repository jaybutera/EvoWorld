package gameobjects;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.*;

import java.util.Random;

public class CreatureFactory {
    private int x;
    private int worldsize;
    private World world;
    private Random r = new Random();
    private CreatureManager manager;

    public CreatureFactory (int init_pos_x, int worldsize, World world, CreatureManager manager) {
        x = init_pos_x;
        this.world = world;
        this.manager = manager;
        this.worldsize = worldsize;
    }

    public TestCreature getTestCreature(int id) {
        // Make a body definition for dynamic bodies
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.angle = r.nextInt(100);
        //bodyDef.position.set(x * 90+100,450);
        bodyDef.position.set(r.nextInt(worldsize),r.nextInt(worldsize));
        x++;

        // Body Fixture gives a shape to a body.
        // In this case a circle
        CircleShape circleShape = new CircleShape();
        circleShape.m_radius = 2f;

        // Body fixture definition
        FixtureDef circFixtureDef = new FixtureDef();
        circFixtureDef.shape = circleShape;
        circFixtureDef.density = 1f; // Gives mass of 20
        circFixtureDef.friction = 0.4f;
        circFixtureDef.filter.categoryBits = EntityType.Creature.getCategoryBit();
        circFixtureDef.filter.maskBits = /*EntityType.Creature.getCategoryBit() & */ EntityType.Wall.getCategoryBit();
        Body new_body = world.createBody(bodyDef);
        // Assign body definition to body
        Fixture new_body_fix = new_body.createFixture(circFixtureDef);

        // Mouth shape
        CircleShape mouthShape = new CircleShape();
        mouthShape.m_radius=2.1f;

        // Mouth sensor fixture definition
        FixtureDef mouthFixtureDef = new FixtureDef();
        mouthFixtureDef.shape = mouthShape;
        mouthFixtureDef.isSensor = true;
        mouthFixtureDef.filter.categoryBits = EntityType.CreatureMouthSensor.getCategoryBit();
        // Mouth sensor collides with food
        mouthFixtureDef.filter.maskBits = EntityType.Food.getCategoryBit();
        Fixture mouth_fix = new_body.createFixture(mouthFixtureDef);

        TestCreature phil = new TestCreature(new_body, manager, circleShape.m_radius,id);
        new_body_fix.setUserData( new UserDataContainer(phil.getEntityType(), phil) );
        mouth_fix.setUserData( new UserDataContainer(EntityType.CreatureMouthSensor, phil) );


        return phil;
    }
}
