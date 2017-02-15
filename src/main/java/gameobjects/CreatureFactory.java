package gameobjects;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.*;

public class CreatureFactory {
    private int x;
    private World world;

    public CreatureFactory (int init_pos_x, World world) {
        x = init_pos_x;
        this.world = world;
    }

    public TestCreature getTestCreature(int id) {
        // Make a body definition for dynamic bodies
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.angle = 0;
        bodyDef.position.set(x * 50+100,200);
        x++;

        // Body Fixture gives a shape to a body.
        // In this case a circle
        CircleShape circleShape = new CircleShape();
        circleShape.m_radius = 15;

        FixtureDef circFixtureDef = new FixtureDef();
        circFixtureDef.shape = circleShape;
        circFixtureDef.density = 1;
        circFixtureDef.friction = 0.1f;
        Body new_body = world.createBody(bodyDef);
        // Assign body definition to body
        Fixture new_body_fix = new_body.createFixture(circFixtureDef);

        TestCreature phil = new TestCreature(new_body,circleShape.m_radius,id,world);
        new_body_fix.setUserData( new UserDataContainer(phil.getEntityType(), phil) );


        return phil;
    }
}
