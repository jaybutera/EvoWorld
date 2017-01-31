package gameobjects;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class CreatureFactory {
    public static Creature getCreature(int id, World world) {
        // Make a body definition for dynamic bodies
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.angle = 0;
        bodyDef.position.set(0, (float) (Math.random() * 10));

        // Body Fixture gives a shape to a body.
        // In this case a circle
        CircleShape circleShape = new CircleShape();
        circleShape.m_radius = 1;

        FixtureDef boxFixtureDef = new FixtureDef();
        boxFixtureDef.shape = circleShape;
        boxFixtureDef.density = 1;
        boxFixtureDef.friction = 0.1f;
        Body new_body = world.createBody(bodyDef);
        // Assign body definition to body
        new_body.createFixture(boxFixtureDef);

        return new TestCreature(new_body, 1f, id, world);
    }
}
