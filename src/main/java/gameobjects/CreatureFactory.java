package gameobjects;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class CreatureFactory {
    static int x = 0;
    public static Creature getCreature(int id, World world) {
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
        new_body.createFixture(circFixtureDef);

        Creature phil = new TestCreature(new_body,circleShape.m_radius,id,world);
        circFixtureDef.setUserData( phil.chems );

        return phil;
    }
}
