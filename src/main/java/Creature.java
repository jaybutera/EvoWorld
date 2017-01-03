import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import toolbox.Vector3f;
import server.messages.PBCreatureOuterClass.PBCreature;

public abstract class Creature extends DynamicEntity {
	private int id;

	public Creature (Body body, float scale) {
		super(body, scale);
	}

	public byte[] serialize () {
		Vec2 position = body.getPosition();
		PBCreature c_serial = PBCreature.newBuilder()
				.setId(id)
				.setX(position.x)
				.setY(position.y)
				.setR(body.getAngle())
				.setS(scale)
				.build();

		System.out.println(c_serial.toString());

		return c_serial.toByteArray();
	}

	public abstract void action (float[] a);
    public abstract float[] observation ();
}
